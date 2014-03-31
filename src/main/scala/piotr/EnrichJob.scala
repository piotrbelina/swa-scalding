package piotr

import com.twitter.scalding._
import java.util.UUID

class EnrichJob(args: Args) extends Job(args) {
  Tsv("data/input.tsv", ('timestamp, 'event, 'url, 'cookie))
    .read
    .map ('event -> 'uuid) { event: String => UUID.randomUUID().toString }
    .write(Tsv("data/enriched.tsv"))
}
