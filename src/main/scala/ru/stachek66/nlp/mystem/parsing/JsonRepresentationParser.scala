package ru.stachek66.nlp.mystem.parsing

import org.json.JSONArray
import ru.stachek66.nlp.mystem.model.Info

/**
 * alexeyev 
 * 31.08.14.
 */
object JsonRepresentationParser {
  //todo:

  def toInfo(json: String): Traversable[Info] = toInfo(new JSONArray(json))

  //todo:
  private def toInfo(json: JSONArray): Traversable[Info] = {
    val stuff = (for (i <- 0 to json.length - 1) yield {
      val item = json.getJSONObject(i)
      val initial = item.getString("text")
      val analysis = item.getJSONArray("analysis")
      val result = for (j <- 0 to analysis.length - 1) yield {
        val anItem = analysis.getJSONObject(j)
        new Info(
          initial,
          anItem.getString("lex"),
          0d,
          GrammarInfoParsing.toGrammarInfo(anItem.getString("gr")))
      }
      //todo:
      result.headOption
    })
    stuff.flatten
  }

  //todo:
  private def toJson(info: Info): JSONArray = throw new NotImplementedError

  def toJsonString(info: Info): String = toJson(info).toString

}