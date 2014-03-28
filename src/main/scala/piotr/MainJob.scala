package piotr

import com.twitter.scalding._
import piotr.swa.Data
import com.backtype.hadoop.pail.{PailStructure, PailSpec, Pail}
import com.backtype.cascading.tap.PailTap
import piotr.pail.{SplitDataPailStructure, DataPailStructure}
import com.twitter.scalding.commons.source.PailSource
import java.util
import cascading.tap.Tap

class MainJob (args : Args) extends Job(args) {

//  override def ioSerializations = super.ioSerializations :+ com.backtype.hadoop.ThriftSerialization.class


  Tsv("data/input.tsv", ('timestamp, 'event, 'url, 'cookie))
    .read
    .map('timestamp -> 'hour_bucket) { timestamp: Int => (timestamp / 3600) }
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
