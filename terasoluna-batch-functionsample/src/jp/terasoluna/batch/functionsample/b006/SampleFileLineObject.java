package jp.terasoluna.batch.functionsample.b006;

import jp.terasoluna.fw.file.annotation.FileFormat;
import jp.terasoluna.fw.file.annotation.InputFileColumn;

@FileFormat(encloseChar = '"')
public class SampleFileLineObject {

    @InputFileColumn(columnIndex = 0)
    private String code = null;

    @InputFileColumn(columnIndex = 1)
    private String name = null;

    @InputFileColumn(columnIndex = 2)
    private String name_kana = null;

    @InputFileColumn(columnIndex = 3)
    private String sex = null;

    @InputFileColumn(columnIndex = 4)
    private String telnum = null;

    @InputFileColumn(columnIndex = 5)
    private String mail = null;

    @InputFileColumn(columnIndex = 6)
    private String birth = null;

    @InputFileColumn(columnIndex = 7)
    private String age = null;

    @InputFileColumn(columnIndex = 8)
    private String hometown = null;

    @InputFileColumn(columnIndex = 9)
    private String bloodtype = null;

    /**
     * code���擾����B
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * code��ݒ肷��B
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * name���擾����B
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * name��ݒ肷��B
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * name_kana���擾����B
     * @return name_kana
     */
    public String getName_kana() {
        return name_kana;
    }

    /**
     * name_kana��ݒ肷��B
     * @param name_kana
     */
    public void setName_kana(String name_kana) {
        this.name_kana = name_kana;
    }

    /**
     * sex���擾����B
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * sex��ݒ肷��B
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * telnum���擾����B
     * @return telnum
     */
    public String getTelnum() {
        return telnum;
    }

    /**
     * telnum��ݒ肷��B
     * @param telnum
     */
    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    /**
     * mail���擾����B
     * @return mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * mail��ݒ肷��B
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * birth���擾����B
     * @return birth
     */
    public String getBirth() {
        return birth;
    }

    /**
     * birth��ݒ肷��B
     * @param birth
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }

    /**
     * age���擾����B
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * age��ݒ肷��B
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * hometown���擾����B
     * @return hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * hometown��ݒ肷��B
     * @param hometown
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * bloodtype���擾����B
     * @return bloodtype
     */
    public String getBloodtype() {
        return bloodtype;
    }

    /**
     * bloodtype��ݒ肷��B
     * @param bloodtype
     */
    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public SampleFileLineObject(String code, String name, String name_kana,
            String sex, String telnum, String mail, String birth, String age,
            String hometown, String bloodtype) {
        this.code = code;
        this.name = name;
        this.name_kana = name_kana;
        this.sex = sex;
        this.telnum = telnum;
        this.mail = mail;
        this.birth = birth;
        this.age = age;
        this.hometown = hometown;
        this.bloodtype = bloodtype;
    }

    public SampleFileLineObject() {

    }

    public String toString() {
        return this.code;
    }

}
