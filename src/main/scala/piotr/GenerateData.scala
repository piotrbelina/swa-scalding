package piotr

import piotr.swa._
import com.backtype.hadoop.pail.Pail

/**
 * Created by bela on 27.03.14.
 */
class GenerateData(path: String) {

  val pail = new Pail[Data](path)

  def getData(time: Int, field: DataUnit._Fields, value: Any): Data = {
    new Data(new Pedigree(time), new DataUnit(field, value))
  }

  def getPerson(field: PersonID._Fields, value: Any) = {
    new PersonID(field, value)
  }

  def generatePeople() {
    val os = pail.openWrite()

    val p1: PersonID = getPerson(PersonID._Fields.USER_ID, 1L)
    val p2: PersonID = getPerson(PersonID._Fields.USER_ID, 2L)
    val p3: PersonID = getPerson(PersonID._Fields.USER_ID, 3L)
    val c1a: PersonID = getPerson(PersonID._Fields.COOKIE, "cookie_1a")
    val c1b: PersonID = getPerson(PersonID._Fields.COOKIE, "cookie_1b")
    val c2a: PersonID = getPerson(PersonID._Fields.COOKIE, "cookie_2a")

    os.writeObject(getData(1395933449, DataUnit._Fields.EQUIV, new EquivEdge(p1, c1a)))
    os.writeObject(getData(1395933449, DataUnit._Fields.EQUIV, new EquivEdge(p1, c1b)))
    os.writeObject(getData(1395933449, DataUnit._Fields.EQUIV, new EquivEdge(p2, c2a)))

    os.close()
  }

  def generate() = {
    generatePeople()
  }
}

object GenerateData {
  def main (args: Array[String]) {
    new GenerateData(new Paths("data/swa").newData).generate()
  }
}
