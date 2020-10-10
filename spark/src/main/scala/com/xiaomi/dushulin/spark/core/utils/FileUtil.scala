package com.xiaomi.dushulin.spark.core.utils

import scala.io.Source
import org.apache.spark.{SparkConf, SparkContext}

object FileUtil {

  def getResFile(path: String) = {
    val res: String = this.getClass.getClassLoader.getResource(path).getPath
    Source.fromFile(res)
  }
}
