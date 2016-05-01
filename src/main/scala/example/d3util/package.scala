/**
  * Created by giampaolo on 28/03/16.
  */
package object d3util {

  case class Margin(left: Int, right: Int, top: Int, bottom: Int) {
    val width = left + right
    val height = top + bottom
  }


}