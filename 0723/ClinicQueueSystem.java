import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClinicQueueSystem {
    private Deque<Patient> waiting = new ArrayDeque<>();
    private List<Patient> served = new ArrayList<>();
    private Set<Integer> usedNumbers = new HashSet<>();

    public boolean register(Patient patient) {
        if (usedNumbers.contains(patient.getNumber())) {
            System.out.println("掛號失敗，號碼重複：" + patient.getNumber());
            return false;
        }
        usedNumbers.add(patient.getNumber());
        waiting.offer(patient);
        System.out.println("掛號成功：" + patient);
        return true;
    }

    public void callNext() {
        Patient patient = waiting.poll();
        if (patient == null) {
            System.out.println("目前無人等待");
            return;
        }
        served.add(patient);
        System.out.println("叫號：" + patient);
    }

    public void peekNext() {
        Patient patient = waiting.peek();
        if (patient == null) {
            System.out.println("下一位：目前無人等待");
        } else {
            System.out.println("下一位：" + patient);
        }
    }

    public void printWaitingList() {
        System.out.println("等待清單：" + waiting);
    }

    public void printDepartmentWaitingCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (Patient patient : waiting) {
            String department = patient.getDepartment();
            counts.put(department, counts.getOrDefault(department, 0) + 1);
        }
        System.out.println("各科別等待人數：" + counts);
    }

    public void printTotalServed() {
        System.out.println("總服務人數：" + served.size());
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.register(new Patient(1, "Amy", "內科"));
        clinic.register(new Patient(2, "Ben", "外科"));
        clinic.register(new Patient(3, "Cara", "內科"));
        clinic.register(new Patient(2, "Dora", "牙科"));

        clinic.printWaitingList();
        clinic.printDepartmentWaitingCounts();
        clinic.peekNext();

        clinic.callNext();
        clinic.callNext();

        clinic.printDepartmentWaitingCounts();
        clinic.printTotalServed();

        clinic.callNext();
        clinic.callNext();
    }
}
