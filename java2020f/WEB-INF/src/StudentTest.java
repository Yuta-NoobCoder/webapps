public class StudentTest {
    public 
  static
   void main( String[] args) {
      Student student = new Student();
      student.take("数学",2, 85);
      student.take("英語",3,95);
      String[] subs=student.list();
      for(int i=0; i< subs.length; i++) {
        System.out.println(subs[i]);
      }
    }
  }
  