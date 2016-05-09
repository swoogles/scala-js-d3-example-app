package example

import bill.d3.TreeData
import scala.scalajs.js
import scala.scalajs.js.Dynamic
import org.singlespaced.d3js.{Link, Tree, d3}
import org.singlespaced.d3js.d3.Primitive

@js.native
trait AnimalNode extends js.Object {
  val parent: String = js.native
  val name: String = js.native
  val children: js.Array[AnimalNode] = js.native
}

object ScalaJSExample extends js.JSApp with TreeData {

  def main(): Unit =
    d3.json("json-example.json", (error: js.Any, json: js.Any) => {

      val jsonTypedFromFile = json.asInstanceOf[AnimalNode]

      val width = 960.0
      val height = 500.0

      val svg = d3.select("#tree").append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")

      val tupledDimensions = (width, height)

      val dynamicTree: Tree[Dynamic] = d3.layout.tree().size(tupledDimensions)
      val animalNodeTree: Tree[AnimalNode] = d3.layout.tree().size(tupledDimensions)

      val dynamicNodes = dynamicTree.nodes(jsonDynamicFromTrait)
      val animalNodes = animalNodeTree.nodes(jsonTypedFromFile)

      val dynamicLinks = dynamicTree.links(dynamicNodes)
      val animalNodeLinks = animalNodeTree.links(animalNodes)
      val hardcodedSampleCurve = "M180,373.75C270,373.75 270,345 360,345": Primitive

      svg
        .append("path")
        .attr("class", "link")
        .style("stroke-width", 5)
        .attr("d", hardcodedSampleCurve) // The line is drawn if I pass the hard-coded value.

      svg // Approach #1
        .data(dynamicLinks)
        .append("path")
        .attr("class", "link")
        .style("stroke-width", 5)
        .attr("d", (myJson: Link[Dynamic], x: Int, y: js.UndefOr[Int]) => {
          // TODO Draw the line between source and target
          hardcodedSampleCurve // This is never reached.
        })

      svg // Approach #2
        .data(animalNodeLinks)
        .append("path")
        .attr("class", "link")
        .style("stroke-width", 5)
        .attr("d", (myJson: Link[AnimalNode], x: Int, y: js.UndefOr[Int]) => {
          // TODO Draw the line between source and target
          hardcodedSampleCurve // This is never reached.
        })

      // Approach #3
      // Something that would get me to this point with the right types to use this signature
      // .attr("d", (myJson: Link[Node], x: Int, y: js.UndefOr[Int]) => { ... }

      val diagonal = d3.svg.diagonal() // Ultimately I want to draw Diagonals across all the links.

      println("Finished without drawing any real paths.")
    }
    )

}

