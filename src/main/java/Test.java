import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import au.com.bytecode.opencsv.CSVReader;

class Test {
    public static void main(String[] args) throws IOException {


        CSVReader reader = new CSVReader(new FileReader("C:\\code\\krypton\\eclipse\\studio-wizard\\CSV2Excel\\src\\main\\resources\\data.csv"));
        String[] line;

        int r = 0;
        int count = 0;
        Workbook wb = null;
        CreationHelper helper = null;
        Sheet sheet = null;
        int id=0;
        while ((line = reader.readNext()) != null) {
            if (count ==0) {
                wb = new HSSFWorkbook();
                helper = wb.getCreationHelper();
                sheet = wb.createSheet("new sheet");
            }
            count ++;
            Row row = sheet.createRow((short) r++);
            for (int i = 0; i < line.length; i++){
                    row.createCell(i).setCellValue(helper.createRichTextString(line[i]));
                }
            if (count ==30000) {
                FileOutputStream fileOut = new FileOutputStream(id+"krypton.xls");
                wb.write(fileOut);
                fileOut.close();
                count = 0;
                r=0;
                id++;
                wb=null;
            }
        }
        if(wb!=null){
            FileOutputStream fileOut = new FileOutputStream(id+"krypton.xls");
            wb.write(fileOut);
            fileOut.close();
        }
    }
}