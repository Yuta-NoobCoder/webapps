import java.util.Hashtable;

public class Student {
    Hashtable<String,Subject> grades;  // 受講科目
    
    public Student() {
        grades = new Hashtable<String,Subject>();
    }

    public void take(String kamoku, int unit, int point) {
        Subject sub = new Subject(unit, point);
        grades.put(kamoku, sub);
    }

    public String[] list() {
        String[] names = grades.keySet().toArray(new String[grades.keySet().size()]);//科目名の配列
        String[] subs = new String[names.length];
        for(int i=0; i<subs.length; i++) {
            subs[i]="  科目名:"+ names[i];
            subs[i]+="　単位数:"+ grades.get(names[i]).getUnit();
            subs[i]+="　成績:"+ grades.get(names[i]).getPoint();
        }
        return subs;
    }

    public float getGPA() {
        
        int unit;
        int point;
        int unit_sum = 0; // 単位数
        int point_sum = 0; // 成績(4段階) * 単位数
        float mean;
        String[] names = grades.keySet().toArray(new String[grades.keySet().size()]);//科目名の配列
        
        for(String name : names) {
            
            Subject sub = grades.get("name");
            unit = sub.getUnit();
            point = sub.getPoint();

            if(point >= 90)
                point_sum += 4 * unit;
            else ifpoint >= 80)
                point_sum += 3 * unit;
            else if(point >= 70)
                point_sum += 2 * unit;
            else if(point >= 60)
                point_sum += unit;
            
            unit_sum += unit;
            }

            mean = (float)point_sum / unit_sum;

            return mean;
    }
}