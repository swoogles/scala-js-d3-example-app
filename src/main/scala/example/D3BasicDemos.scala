package bill.d3
import d3util.Margin

import scala.scalajs.js
import scala.scalajs.js.Dynamic
import org.singlespaced.d3js.d3

import scala.scalajs.js.Tuple2
import org.singlespaced.d3js.Tree
import org.singlespaced.d3js.d3.Primitive
import org.singlespaced.d3js.svg.diagonalModule.Node
import org.singlespaced.d3js.svg.diagonalModule.Link

import scala.language.implicitConversions

trait D3DemoTrait extends TreeData {

  def drawDiagonal = {
    val margin = Margin(top = 20, right = 20, bottom = 30, left = 50)

    val width = 960.0 - margin.width
    val height = 500.0 - margin.height

    val svg = d3.select("#tree-lines-test").append("svg")
      .attr("width", width)
      .attr("height", height)
      .append("g")
      .attr("transform", "translate(" + 50 + "," + 50 + ")");

    val projector =
      (d: Node, num: Double) => { js.Tuple2(d.x, d.y) }

    val linkPoints = (value: Link[Node]) => value.target.x

    val tupledDimensions: Tuple2[Double, Double] = (width, height)

    val tree: Tree[Dynamic] =
      d3.layout.tree()
        .size(tupledDimensions)

    val root: Dynamic = treeDataJson // Json tree.

    // val diagonal: Diagonal[Node[Link]] = d3.svg.diagonal()
    val diagonal = d3.svg.diagonal()
      .projection(projector)

    val nodes =
      tree
        .nodes(root)

    val links = tree.links(nodes)

    svg
      .data(links)
      .append("path")
      .attr("class", "link")
      .style("stroke-width", 5)
    // .attr("d", ()=>"M180,373.75C270,373.75 270,345 360,345") // This is the actual value I want. The line is drawn if I pass the hard-coded value.

     .attr("d", (value: Link[Node], x: Int,y:  js.UndefOr[Int]) => {
       diagonal(value, 100.0)
       "blah": Primitive
     })

  }

}

object D3Demos extends D3DemoTrait