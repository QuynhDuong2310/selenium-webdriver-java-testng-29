package javaTester;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class Topic_01_Data_Type {
    // By cũng lalaf một kiểu dữ liệu
    By by;
    //Data type:
    // Kiểu nguyên thủy (Primitive type)
    // Số nguyên: byte - short - int - long
    int a = 10;
    // Số thực : float - double : Thập phân
    float fNumber = 9.99f;
    double dNumber = 35.000000d;
    // Kí tự: char - Đại diện cho 1 kí tự  - Nằm trong dấu nháy đơn - char c = 'M';
    char c = 'M';
    //logic: Boolean
    boolean status = true;
    // Kiểu tham chiếu (Reference type)
    //class
    //object
    //array
    String[] studentName = {"Quynh", "My"}; // Khi mình khai báo ntn tức là mình set cứng số lượng ngay từ đầu
    //collection: List/ Set/ Queue
    List<String> studentAddress = new ArrayList<String>(); //Class ArrayList sẽ implement cái  List, cái array này nó là kiểu dữ liệu động, nó k bắt buộc phải khai báo số lượng ngay từ đầu

    //String - Chuỗi kí tự
    String name = "Quynh";


    // Khai báo 1 biến để lưu trữ 1 loại dự liệu nào đó
    // Access modifier: Phạm vi truy cập (Public / private / Protected / Default)
    // Kiểu dữ liệu - Tên biến
    // Giá trị của biến  - Gán vào với phép =
    // Nếu như k gán : Dữ liệu mặc định, khi mà mình chưa gán thì nó có thẻ = 0 or null

}
