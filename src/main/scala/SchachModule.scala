package model

import com.google.inject.AbstractModule
import model.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.ScalaModule

class SchachModule extends AbstractModule with ScalaModule {

  def configure() = {
    val config: scala.xml.Elem = scala.xml.XML.loadFile("config.xml")
    val savesystem: String = (config \\ "slmanager" \ "@type").text
    if (savesystem.equals("JSON")){
      bind[FileIOInterface].to[model.fileIOComponent.fileIoJsonImpl.FileIO]
    }
  }

}