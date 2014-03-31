package piotr

import com.twitter.scalding._
import java.net.URL
import cascading.flow.FlowProcess
import cascading.operation.{BaseOperation, OperationCall, BufferCall, Buffer}
import java.util
import cascading.tuple.TupleEntry



class BouncesJob(args: Args) extends Job(args) {

  def createState = new {
    var lastTimestamp: String = null
    def release() {}
  }

  Tsv("data/enriched.tsv", ('timestamp, 'event, 'url, 'cookie, 'uuid))
    .read
    .map ('url -> 'domain) { urlStr: String => {
      val url = new URL(urlStr)
      url.getAuthority
    }}
//    .groupAll { _.sortBy('timestamp) }


//    .pipe
//      .using { createState }
//    .flatMap[String, (String, String)] ('timestamp -> ('previousTimestamp, 'currentTimestamp)) { case (accu, timestamp) =>
//      if (accu.lastTimestamp == null) {
//        accu.lastTimestamp = timestamp
//        List(((-1).toString, timestamp))
//      } else {
//        val zipped = List((accu.lastTimestamp, timestamp))
//        accu.lastTimestamp = timestamp
//        zipped
//      }
//    }

    // https://github.com/twitter/scalding/blob/develop/scalding-core/src/test/scala/com/twitter/scalding/ScanLeftTest.scala#L34
    .insert('temp, 0L)
    .insert('temp2, 0L)
    .groupBy ('cookie) { group =>
      group.sortBy('timestamp)
        .scanLeft(('timestamp, 'temp, 'temp2) -> ('originalTimestamp, 'duration, 'visitId))((0L, 0L, 0L)) {
        (firstLine: (Long, Long, Long), secondLine: (Long, Long, Long)) =>
          var delta = secondLine._1 - firstLine._1

          val visitId =  if (delta > (60 * 60)) {
            firstLine._3 + 1L
          } else {
            firstLine._3
          }
          (secondLine._1, delta, visitId)
      }
    }
    .discard('originalTimestamp, 'temp, 'temp2)
    .filter('timestamp) { x: Any => x != null }

    .groupBy ('cookie, 'visitId) { group =>
      group.size
    }
    .groupAll { _.size }


    .debug
    .write(Tsv("data/bounces.tsv"))

}
