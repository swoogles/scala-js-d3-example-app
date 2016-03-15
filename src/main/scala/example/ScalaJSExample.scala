package example

import org.singlespaced.d3js.Ops._
import org.singlespaced.d3js.d3

import scala.scalajs.js

@js.native
trait MyRootJson extends js.Object {
  val root:js.Array[MyJson]= js.native
}



@js.native
trait MyJson extends js.Object {
  val color:String= js.native
}

object ScalaJSExample extends js.JSApp {

  def main(): Unit = {
    /**
      * Answer for http://stackoverflow.com/questions/36010776/d3-json-parsing-in-scala-js
      */


    val rectXFunVpc = (d: MyJson, i: Int) => i * 30;
    val rectColorFun =(d: MyJson) => d.color;

    val svg = d3.select("body").append("svg").attr("width", "100%").attr("height", "450px")

    d3.json("json-example.json", (error: js.Any, json: js.Any) => {

      val dataArray = json.asInstanceOf[MyRootJson].root

      val sel = svg.selectAll("rect").data(dataArray)
      sel.enter()
        .append("rect")
        .attr("x", rectXFunVpc)
        .attr("y", 20)
        .attr("width", 20)
        .attr("height", 10)
        .style("fill", rectColorFun)
      ()
    })
  }
}
