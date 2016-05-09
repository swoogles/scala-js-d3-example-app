package bill.d3

import d3util.Margin

import scala.scalajs.js
import scala.scalajs.js.{Array, Dynamic, Tuple2}
import org.singlespaced.d3js.{Link, Tree, d3}
import org.singlespaced.d3js.d3.Primitive
import org.singlespaced.d3js.selection.Update
import org.singlespaced.d3js.svg.diagonalModule.Node

//import org.singlespaced.d3js.svg.diagonalModule.Link

import scala.language.implicitConversions

@js.native
trait MyRootJson extends js.Object {
  val root: js.Array[MyJson] = js.native
}


@js.native
trait MyJson extends js.Object {
  val color: String = js.native
  val parent: String = js.native
  val group: String = js.native
  val children: js.Array[MyJson] = js.native
}

trait D3DemoTrait extends TreeData {


  def drawDiagonal = {
    d3.json("json-example.json", (error: js.Any, json: js.Any) => {

      val dataArray = json.asInstanceOf[MyJson]

//      println("dataArray: " + dataArray)
//      dataArray.foreach { x => {
//        println("group: " + x.group)
//        x.children.foreach(subGroup => println("subGroup: " + subGroup.group))
//      }
//      }

      val margin = Margin(top = 20, right = 20, bottom = 30, left = 50)

      val width = 960.0 - margin.width
      val height = 500.0 - margin.height

      val svg = d3.select("#tree").append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + 50 + "," + 50 + ")");

      val projector =
        (d: Node, num: Double) => {
          js.Tuple2(d.x, d.y)
        }

      val linkPoints = (value: Link[Node]) => value.target.x

      val tupledDimensions: Tuple2[Double, Double] = (width, height)

      val tree: Tree[Dynamic] =
        d3.layout.tree()
          .size(tupledDimensions)

      val treeB: Tree[MyJson] =
        d3.layout.tree()
          .size(tupledDimensions)

      val root: Dynamic = treeDataJson // Json tree.

      val diagonal = d3.svg.diagonal()
      //      .projection(projector)

      val nodes =
        tree
          .nodes(root)

      val nodesB =
        treeB
          .nodes(dataArray)

      val links = tree.links(nodes)
      println("num links: " + links.length)

      val linksB = treeB.links(nodesB)
      println("num linksB: " + linksB.length)

      svg
        // .data(links)
        .append("path")
        .attr("class", "link")
        .style("stroke-width", 5)
        .attr("d", "M180,373.75C270,373.75 270,345 360,345") // This is the actual value I want. The line is drawn if I pass the hard-coded value.

      svg
        .data(linksB)
        .append("path")
        .attr("class", "link")
        .style("stroke-width", 5)
        .attr("d", (myJson: Link[MyJson], x: Int, y: js.UndefOr[Int]) => {
//          .attr("d", (myJson: Link[Dynamic], x: Int, y: js.UndefOr[Int]) => {
//          println("myJson: " + myJson.group)
          "blah": Primitive
//          true
        })

      println("Finished Diagonal section but didn't draw any paths.")
    }
    )

  }

}

object D3Demos extends D3DemoTrait