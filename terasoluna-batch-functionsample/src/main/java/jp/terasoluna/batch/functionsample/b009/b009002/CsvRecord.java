package jp.terasoluna.batch.functionsample.b009.b009002;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import jp.terasoluna.fw.file.annotation.FileFormat;
import jp.terasoluna.fw.file.annotation.InputFileColumn;
import jp.terasoluna.fw.file.annotation.OutputFileColumn;
import jp.terasoluna.fw.file.annotation.PaddingType;

/**
 * CSV�t�@�C��1���R�[�h���}�b�s���O����Bean
 */
@FileFormat(lineFeedChar = "\r\n", fileEncoding = "UTF-8", overWriteFlg = true)
public class CsvRecord {

    // ID (1�Ԗڂ̃J����)
    @InputFileColumn(columnIndex = 0)
    @OutputFileColumn(columnIndex = 0, paddingType = PaddingType.LEFT, paddingChar = '0', bytes = 6)
    @NotNull
    private int id = 0;

    // ���� (2�Ԗڂ̃J����)
    @InputFileColumn(columnIndex = 1)
    @OutputFileColumn(columnIndex = 1)
    @NotEmpty
    private String familyName = null;

    // ���O (3�Ԗڂ̃J����)
    @InputFileColumn(columnIndex = 2)
    @OutputFileColumn(columnIndex = 2)
    @NotEmpty
    private String firstName = null;

    // �N�� (4�Ԗڂ̃J����)
    @InputFileColumn(columnIndex = 3)
    @OutputFileColumn(columnIndex = 3)
    @Min(0)
    @Max(99)
    private int age = 0;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * @param familyName the familyName to set
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

}
