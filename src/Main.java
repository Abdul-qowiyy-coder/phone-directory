import java.io.*;
import java.util.*;

class Directory implements Comparable {
    int id;
    String name;
    int Age;
    String address;
    int phoneNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int compareTo(Object ob) throws ClassCastException {
        if (!(ob instanceof Directory))
            throw new ClassCastException("Error");
        int ide = ((Directory) ob).getId();
        return this.id - ide;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int menu = 0;
        System.out.println("Telephone Directory Management System");
        System.out.println();
        System.out.println("1. Accept Data");
        System.out.println("2. Search");
        System.out.println("3. Sort Data");
        System.out.println("4. List of all persons");
        System.out.println("5. Exit");
        boolean quit = false;
        do {
            System.out.print("Please enter a number to choose an option: ");
            menu = scan.nextInt();
            System.out.println();

            switch (menu) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int ID = scan.nextInt();
                    System.out.print("Enter Name: ");
                    String name = scan.next();
                    System.out.print("Enter Age: ");
                    int age = scan.nextInt();
                    System.out.print("Enter Address: ");
                    String address = scan.next();
                    System.out.println("Enter Phone No: ");
                    int no = scan.nextInt();
                    FileWriter fw = new FileWriter(new File("directory.txt"), true);
                    BufferedWriter out = new BufferedWriter(fw);
                    out.write(ID + " " + name  + " " + age + " " + address + " " + no);
                    out.newLine();
                    out.close();
                    break;
                case 2:
                    System.out.print("Enter name to search information: ");
                    String n = scan.next();
                    File f = new File("directory.txt");
                    try {
                        BufferedReader freader = new BufferedReader(new FileReader(
                                f));
                        String s;
                        while ((s = freader.readLine()) != null) {
                            String[] st = s.split(" ");
                            String id = st[0];
                            String nm = st[1];
                            String ag = st[2];
                            String add = st[3];
                            String phoneNo = st[4];
                            if (n.equals(nm)) {
                                System.out
                                        .println("***********Information**************");
                                System.out.println("Age : " + ag);
                                System.out.println("Address : " + add);
                                System.out.println("PhoneNo : " + phoneNo);
                            }
                        }
                        freader.close();
                    } catch (Exception e) {
                    }
                    break;
                case 3:
                    File file = new File("directory.txt");
                    FileInputStream fstream = new FileInputStream(file);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(in));
                    String strLine;
                    ArrayList list = new ArrayList();
                    while ((strLine = br.readLine()) != null) {
                        list.add(strLine);
                    }
                    int j = 0;
                    Directory data[] = new Directory[list.size()];
                    try {
                        Iterator itr;
                        for (itr = list.iterator(); itr.hasNext();) {
                            String str = itr.next().toString();
                            String[] splitSt = str.split(" ");
                            String id = "", nn = "", ag = "", add = "", pno = "";
                            for (int i = 0; i < splitSt.length; i++) {
                                id = splitSt[0];
                                nn = splitSt[1];
                                ag = splitSt[2];
                                add = splitSt[3];
                                pno = splitSt[4];

                            }
                            data[j] = new Directory();
                            data[j].setId(Integer.parseInt(id));
                            data[j].setName(nn);
                            data[j].setAge(Integer.parseInt(ag));
                            data[j].setAddress(add);
                            data[j].setPhoneNo(Integer.parseInt(pno));

                            j++;
                        }

                        BufferedWriter bw = new BufferedWriter(new FileWriter(file,
                                true));
                        Arrays.sort(data);
                        System.out.println("********Sorted by id********");
                        String strVal = "";
                        for (int i = 0; i < 8; i++) {
                            Directory show = data[i];
                            int ide = show.getId();
                            String nnn = show.getName();
                            int agg = show.getAge();
                            String add = show.getAddress();
                            int phone = show.getPhoneNo();
                            System.out.println(ide + " " + nnn + " " + agg + " " + add + " " + phone);
                        }
                    } catch (Exception e) {
                    }
                    break;

                case 4:
                    FileInputStream fis = new FileInputStream(new File(
                            "directory.txt"));
                    DataInputStream dis = new DataInputStream(fis);
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(dis));
                    String st;
                    ArrayList al = new ArrayList();
                    while ((st = reader.readLine()) != null) {
                        al.add(st);
                    }
                    Iterator itr;
                    for (itr = al.iterator(); itr.hasNext();) {
                        String str = itr.next().toString();
                        String[] splitSt = str.split(" ");
                        String id = "", na = "", ag = "", ada = "", ph = "";
                        for (int i = 0; i < splitSt.length; i++) {
                            id = splitSt[0];
                            na = splitSt[1];
                            ag = splitSt[2];
                            ada = splitSt[3];
                            ph = splitSt[4];
                        }
                        System.out.println(id + " " + na + " " + ag + " " + ada + " " + ph);
                    }
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Entry!");
            }
        } while (!quit);
    }
}
