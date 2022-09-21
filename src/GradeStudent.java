//Nhập lớp Scanner vào Java để giúp chúng ta lấy đầu vào từ người dùng.
import java.util.Scanner;

/**
 * Class GradeStudent chứa toàn bộ code của chương trình đánh giá xếp hạng sinh viên Grade Student.
 */
public class GradeStudent {

    // Tạo ra các biến toàn cục để lưu các thông số
    public static Scanner input = new Scanner(System.in);// Tạo đối tượng Scanner lấy đầu vào từ bàn phím.

    public static int weightMidTerm;//Biến lưu trọng số của điểm thi giữa kỳ.
    public static int weightfinalTerm;//Biến lưu trọng số của điểm thi cuối kỳ.
    public static int weightHomework;//Biến lưu trọng số của điểm bài tập về nhà.

    public static double weightedMidtermScore;//Biến lưu điểm số của phần thi giữa kỳ tính dựa trên trọng số (ở dạng double vì có lấy chữ số thập phân).
    public static double weightedFinalExamScore;//Biến lưu điểm số của phần thi cuối kỳ tính dựa trên trọng số (ở dạng double vì có lấy chữ số thập phân).
    public static double weightedHomeworkScore;//Biến lưu điểm số của phần điểm bài tập về nhà tính dựa trên trọng số (ở dạng double vì có lấy chữ số thập phân).
    public static double overallPercentage;//Biến lưu tổng điểm sinh viên đạt được của 3 phần trên tính theo thang điểm 100 (ở dạng double vì có lấy chữ số thập phân).


    /**
     * Khai báo hàm main để Java thực thi.
     *
     * @param args
     */
    public static void main(String[] args) {
        //Gọi hàm begin() để hiển thị thông điệp chào mừng.
        begin();

        //Gọi hàm midTerm() để nhập, tính toán điểm thi giữa kỳ và gán giá trị trả về vào biến weightedMidtermScore
        weightedMidtermScore = midTerm();
        System.out.println("Weighted score: " + weightedMidtermScore + "/" + weightMidTerm);//Hiển thị giá trị điểm số của phần thi giữa kỳ
        System.out.println();

        //Gọi hàm finalTerm() để nhập và tính toán điểm thi cuối kỳ.
        finalTerm();

        //Gọi hàm homework() để nhập và tính toán điểm bài tập về nhà.
        homework();

        //Gọi hàm report() để tính toán và hiển thị kết quả GPA cũng như thông báo nhận xét tương ứng.
        report();

    }


    /**
     * Hàm begin() để hiển thị thông điệp chào mừng.
     */
    private static void begin() {

        System.out.println("This program reads exam/homework scores and reports your overall course grade.");//Hiển thị thông báo mô tả ngắn gọn về chương trình
        System.out.println();

    }

    /**
     * Hàm midTerm() để nhập và tính toán điểm thi giữa kỳ.
     */
    private static double midTerm() {

        System.out.println("Midterm:");

        //Dùng vòng lặp để nhận giá trị nhập vào từ người dùng mà thoả mãn yêu cầu đề bài
        do {
            System.out.println("Weight must be between 0 and 100 and totalWeight (Midterm + Final + Homework) must be 100% (Current: 0%)");
            weightMidTerm = input.nextInt();//Lưu giá trị người dùng nhập vào biến
        } while (weightMidTerm <= 0 || weightMidTerm >= 100);//Điều kiện của trọng số phần thi giữa kì

        System.out.println("Score earned: ");
        int scoreEarned = input.nextInt();//Biến lưu điểm số chưa tăng

        System.out.println("Were scores shifted (1 = yes, 2 = no): ");//Hỏi người dùng điểm thi bạn có được tăng không
        int scoreShifted = input.nextInt();//Lưu giá trị người dùng nhập vào biến
        int shiftAmount = 0;//Biến lưu số điểm đã được tăng và gán giá trị ban đầu là 0 để dùng cho biến totalPoints trong trường hợp người dùng chọn không có điểm tăng.
        if (scoreShifted == 1) {
            System.out.println("Shift amount: ");
            shiftAmount = input.nextInt();//Lưu giá trị người dùng nhập vào biến
        }

        int totalPoints = scoreEarned + shiftAmount;//Biến lưu điểm số đã tăng
        if (totalPoints > 100) totalPoints = 100;//Nếu điểm số trên 100 thì chỉ lấy 100
        System.out.println("Total points: " + totalPoints + "/100");
        return (double) totalPoints * weightMidTerm / 100;//trả về điểm số dạng double tính dựa trên trọng số để đảm bảo yêu cầu có 1 hàm trả về của đề bài.

    }

    /**
     * Hàm finalTerm() để nhập và tính toán điểm thi cuối kỳ.
     */
    private static void finalTerm() {

        System.out.println("Final:");

        //Dùng vòng lặp để nhận giá trị nhập vào từ người dùng mà thoả mãn yêu cầu đề bài
        do {
            System.out.println("Weight must be between 0 and 100 and totalWeight (Midterm + Final + Homework) must be 100% (Current: " + weightMidTerm + "%)");
            weightfinalTerm = input.nextInt();//Lưu giá trị người dùng nhập vào biến
        } while (weightfinalTerm <= 0 || weightfinalTerm >= 100 || weightMidTerm + weightfinalTerm >= 100);//Điều kiện của trọng số phần thi cuối kì có thêm trường hợp tổng 2 trọng số vượt quá 100 thì không thoả vì còn phần homework.

        System.out.println("Score earned: ");
        int scoreEarned = input.nextInt();//Biến lưu điểm số chưa tăng

        System.out.println("Were scores shifted (1 = yes, 2 = no): ");//Hỏi người dùng điểm thi bạn có được tăng không
        int scoreShifted = input.nextInt();//Lưu giá trị người dùng nhập vào biến
        int shiftAmount = 0;//Biến lưu số điểm đã được tăng và gán giá trị ban đầu là 0 để dùng cho biến totalPoints trong trường hợp người dùng chọn không có điểm tăng.
        if (scoreShifted == 1) {
            System.out.println("Shift amount: ");
            shiftAmount = input.nextInt();//Lưu giá trị người dùng nhập vào biến
        }

        int totalPoints = scoreEarned + shiftAmount;//Biến lưu điểm số đã tăng
        if (totalPoints > 100) totalPoints = 100;//Nếu điểm số trên 100 thì chỉ lấy 100
        System.out.println("Total points: " + totalPoints + "/100");
        weightedFinalExamScore = (double) totalPoints * weightfinalTerm / 100;//Lưu vào biến điểm số dạng double tính dựa trên trọng số.
        System.out.println("Weighted score: " + weightedFinalExamScore + "/" + weightfinalTerm);//Hiển thị giá trị điểm số của phần thi cuối kỳ
        System.out.println();
    }

    /**
     * Hàm homework() để nhập và tính toán điểm bài tập về nhà.
     */
    private static void homework() {

        System.out.println("Homework:");

        //Dùng vòng lặp để nhận giá trị nhập vào từ người dùng mà thoả mãn yêu cầu đề bài
        do {
            System.out.println("Weight must be between 0 and 100 and totalWeight (Midterm + Final + Homework) must be 100% (Current: " + (weightMidTerm + weightfinalTerm) + "%)");
            weightHomework = input.nextInt();
        } while (weightfinalTerm <= 0 || weightfinalTerm >= 100 || weightMidTerm + weightfinalTerm + weightHomework != 100);//Điều kiện của trọng số phần thi cuối kì có thêm trường hợp tổng 3 trọng số phải bằng 100.

        System.out.println("Number of assignments: ");
        int assignmentNumber = input.nextInt();//Biến lưu tổng số assignment.

        int totalAsm = 0;//Biến lưu tổng số điểm thực của assingment, gán giá trị ban đầu là 0 rồi cộng dồn tổng số điểm thực của assingment sau.(1)
        int totalAssginmentMax = 0;//Biến lưu tổng số điểm assignment cao nhất đạt được, gán giá trị ban đầu là 0 và cộng dồn tổng số điểm assignment cao nhất sau.(I)

        int asm;//điểm asm thực
        int max;//điểm asm max

        for (int i = 0; i < assignmentNumber; i++) {
//            System.out.println("Assignment " + (i + 1) + " score and max: (score <= max)");
            do {
                System.out.println("Assignment " + (i + 1) + " score and max: (score <= max)");
                asm = input.nextInt();//Lưu giá trị người dùng nhập vào biến
                max = input.nextInt();//Lưu giá trị người dùng nhập vào biến
            } while (asm > max);
            totalAsm += asm;//Cộng dồn tổng điểm số thực của assingment.(2)
            totalAssginmentMax += max;//Cộng dồn tổng số điểm cao nhất.(II)
        }

        System.out.println("How many sections did you attend: ");
        int section = input.nextInt();// Biến lưu số lượng buổi học sinh viên đã đi học.

        int sectionPoint = section * 5;//Biến lưu tổng số điểm chuyên cần của sinh viên, với mỗi buổi được điểm danh, thì sinh viên sẽ được tính 5 điểm.
        if (sectionPoint > 30) sectionPoint = 30;//Nếu điểm số trên 30 thì chỉ lấy 30
        System.out.println("Section points: " + sectionPoint + "/30");

        if (totalAsm > 150) {
            totalAsm = 150;//Tổng điểm số thực tối đa của assingment là 150, nếu vượt quá thì cũng chỉ tính là 150 điểm.
        }

        if (totalAssginmentMax > 150) {
            totalAssginmentMax = 150;//Tổng điểm số tối đa của phần Assignment là 150, nếu vượt quá thì cũng chỉ tính là 150 điểm.
        }

        int totalPoint = totalAsm + sectionPoint;////Biến lưu tổng điểm số của sinh viên gồm tổng điểm số thực của assingment và tổng số điểm chuyên cần.
        int totalMax = totalAssginmentMax + 30;//Tổng điểm tối đa đạt được bao gồm tổng điểm tối đa của phần Assignment và tổng điểm chuyên cần tối đa là 30
        System.out.println("Total points: " + totalPoint + "/" + totalMax);

        weightedHomeworkScore = (double) totalPoint * weightHomework / totalMax;//Lưu vào biến điểm số dạng double tính dựa trên trọng số.
        weightedHomeworkScore = (double) Math.round(weightedHomeworkScore * 10) / 10;//Làm tròn đến 1 chữ số thập phân.
        System.out.println("Weighted score: " + weightedHomeworkScore + "/" + weightHomework);
        System.out.println();

    }

    /**
     * Hàm report() để tính toán và hiển thị kết quả GPA cũng như thông báo nhận xét tương ứng.
     */
    private static void report() {

        overallPercentage = weightedMidtermScore + weightedFinalExamScore + weightedHomeworkScore;//Biến lưu tổng điểm sinh viên đạt được của 3 môn tính theo thang điểm 100
        System.out.println("Overall percentage: " + overallPercentage);

        double grade;//Biến grade lưu điểm trung bình các môn học, gán giá trị ban đầu là 4.0

        //Hàm if else để tính GPA dựa vào điểm số trung bình và đưa ra nhận xét.
        if (overallPercentage >= 85) {
            grade = 3.0;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("You are Excellent!");
        } else if (overallPercentage >= 75 & overallPercentage < 85) {
            grade = 2.0;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("You are Good!");
        } else if (overallPercentage >= 60 & overallPercentage < 75) {
            grade = 1.0;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("You are Fairly Good!");
        } else if (overallPercentage < 60) {
            grade = 0.0;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("You Fail!");
        }

    }
}