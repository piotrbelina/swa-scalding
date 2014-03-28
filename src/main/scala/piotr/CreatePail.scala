package piotr

import com.backtype.hadoop.pail.Pail
import com.backtype.hadoop.pail.PailStructure
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.conf.Configuration
import piotr.swa.Data
import piotr.pail.{SplitDataPailStructure, DataPailStructure}

/**
 * Created by bela on 27.03.14.
 */
class CreatePail {
  def createPail(path: String, structure: PailStructure[Data]) = {
    val pail = Pail.create(FileSystem.getLocal(new Configuration), path, structure)
    pail
  }
}

object CreatePail {
  def main(args: Array[String]) {
    val paths = new Paths("data/swa")

    val creator = new CreatePail
    creator.createPail(paths.newData, new DataPailStructure)
    creator.createPail(paths.masterData, new SplitDataPailStructure)
  }
}
