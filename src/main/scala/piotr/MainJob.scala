package piotr

import com.twitter.scalding._
import java.util.UUID

class MainJob (args : Args) extends Job(args) {

  initialize()
    .thenDo(mainJob _)



  def initialize() = {
    Tsv("data/enriched.tsv", ('timestamp, 'event, 'url, 'cookie, 'uuid)).read
  }

  def mainJob(events: RichPipe) : RichPipe = {
    events.map('timestamp -> 'hour_bucket) { timestamp: Int => (timestamp / 3600) }
      .flatMap('hour_bucket -> ('granularity, 'bucket)) { bucket: Int => Array(
      ("h", bucket),
      ("d", bucket / 24),
      ("w", bucket / (24 * 7)),
      ("m", bucket / (24 * 7 * 28))
    )}
      .groupBy('granularity, 'bucket, 'url) { _.size }
      .debug
      .write(Tsv("data/output.tsv"))
  }



}
