import java.util.Scanner;

//import Controller.PublicToiletDAO;
//import Controller.PublicToiletManager;
import view.MainMenu;

public class PublicGymMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        while (true) {
            MainMenu.mainView();
            menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1:
                    // PublicToiletManager.getToilets(sc);
                    break;
                case 2:
                    // PublicToiletDAO.insertToilets();
                    break;
                case 3:
                    // PublicToiletDAO.deleteToilets();
                    break;
                case 4:
                    // PublicToiletManager.searchToilets(sc);
                    break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("올바른 메뉴 입력");
                    break;
            }
        }
    }
}
