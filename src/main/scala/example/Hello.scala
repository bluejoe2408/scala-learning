package example

object Hello extends Greeting with App {
  println(finalSql)
}

trait Greeting {
  lazy val finalSql = s"""
         |select
         |   feature_detail
         |   ,feature
         |   ,feature_group
         |from (
         |  select
         |      feature  as feature_detail
         |      ,mapping_detail as feature
         |      ,mapping_general as feature_group
         |      ,row_number() over(partition by feature order by mapping_detail) as rk
         |  from shopee.regbi_oa_direct_feature_list
         |)
         |where rk = 1
         |""".stripMargin
}
