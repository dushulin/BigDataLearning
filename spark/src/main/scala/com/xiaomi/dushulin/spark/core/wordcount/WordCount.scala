package com.xiaomi.dushulin.spark.core.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    //1.创建SparkConf并设置App名称
    val conf = new SparkConf().setAppName("wordcount").setMaster("local")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc = new SparkContext(conf)
    //3.读取指定位置文件
    val lineRDD: RDD[String] = sc.textFile("input")
    //4.读取的一行一行的数据分解成一个一个的单词（扁平化）
    val wordRDD: RDD[String] = lineRDD.flatMap(line => line.split(" "))
    //5. 将数据转换结构：(hello,1)(dushulin,1)(tvv,1)
    val wordToOneRDD: RDD[(String, Int)] = wordRDD.map(word => (word, 1))
    //6.将转换结构后的数据进行聚合处理
    val wordToSumRDD: RDD[(String, Int)] = wordToOneRDD.reduceByKey((v1, v2) => v1 + v2)
    //7.将统计结果采集到控制台打印
    val wordCountArray: Array[(String, Int)] = wordToSumRDD.collect()
    wordCountArray.foreach(println)
    //8.关闭
    sc.stop()
  }

}
