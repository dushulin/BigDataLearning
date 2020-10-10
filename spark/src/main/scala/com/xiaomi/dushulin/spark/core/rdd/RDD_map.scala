package com.xiaomi.dushulin.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_map {

  val conf = new SparkConf().setAppName("RDD_map").setMaster("local[*]")
  val sparkContext = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

//    testMap()
    getUrl("apache.log")
    sparkContext.stop()
  }

  def testMap(): Unit = {
    val rdd: RDD[Int] = sparkContext.makeRDD(List(1, 2, 3, 4))
    /**
     * 函数签名：def map[U: ClassTag](f: T => U): RDD[U]
     */
    val rdd1: RDD[Int] = rdd.map(
      num => {
        num * 2
      }
    )
    val rdd2: RDD[String] = rdd1.map(
      num => {
        "**" + num
      }
    )

    rdd1.collect().foreach(println)
    rdd2.collect().foreach(println)
  }

  def getUrl(path: String) = {
    val rdd: RDD[String] = sparkContext.textFile(path)
    rdd.map(
      line => {
        val strings: Array[String] = line.split(" ")
        val url: String = strings(strings.length - 1)
        url
      }
    )
    rdd.saveAsTextFile("output1")
  }

}
