Feature: SELECT QUERY EXECUTION

  Background: Database baglantisi kurma
    * Database baglantisi kurulur.

  # Database içindeki "deposits" toblosunda
  # "amount" değeri 200$ ile 500$
  # arasında olan user_id’leri doğrulayınız

  @query01
  Scenario: Deposits tablosunda amount degerine gore id sorgulama testi.

    * SQL Query01 hazirlanir ve calistirilir.
    * SQL Query01 sonuclari test edilir.
    * Database baglantisi kapatilir.

  # Database içindeki "cron_schedules" tablosunda ilk 2 kaydın "name" bilgisini doğrulayınız.
  @query02
  Scenario: "cron_schedules" tablosunda ilk 2 kaydın "name" bilgisini dogrulama testi.

    * SQL Query02 hazirlanir ve calistirilir.
    * SQL Query02 sonuclari test edilir.
    * Database baglantisi kapatilir.