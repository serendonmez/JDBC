Feature: UPDATE QUERY EXECUTION
  Background: Database baglantisi kurulumu
    * Database baglantisi kurulur.

  #"users" tablosunda sondan bir önceki harfi e olan "usernamelerin"
  # "mobile" numarasını update ediniz
  @PreparedUpdate01
  Scenario: Users tablosunda mobile number update testi.

    * UpdateQuery01 hazirlanir ve calistirilir.
    * UpdateQuery01 sonuclari test edilir.
    * Database baglantisi kapatilir.


  # admin_notifications tablosunda ki "id=?" olan kullanıcının
  # "is_read=0" olan bildirimlerini '1' Olarak Update edip doğrulayınız.
  @PreparedUpdate02
  Scenario: Verilen id numarasi ile is_read bilgisi update etme testi.

    * UpdateQuery02 hazirlanir ve calistirilir.
    * UpdateQuery02 sonuclari test edilir.
    * Database baglantisi kapatilir.


  #Database üzerinde "divice_tokens" tablosuna istenen veriyi tek sorguda ekleyiniz.
 # "INSERT INTO device_tokens (id, user_id, is_app,token)VALUES(?,?,?,?);";

  @insertquery01
  Scenario: divice_tokens tablosuna tekil veri ekleme testi.

    * InsertQuery01 hazirlanir ve calistirilir.
    * InsertQuery01 sonuclari test edilir.
    * Database baglantisi kapatilir.


    # Update_logs tablosunda "id=?" değerine göre
    # bir datayı siliniz ve silindiğini doğrulayınız

  @deletequery01
  Scenario: update_logs tablosunda verilen id degerine gore data silme testi.

    * DeleteQuery01 hazirlanir ve calistirilir.
    * DeleteQuery01 sonuclari test edilir.
    * Database baglantisi kapatilir.