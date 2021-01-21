package scheduleAtZero;
public class proc{
	int pid;
	int exec;
	int priority;
	int arr;
	public proc(int pid, int exec, int priority, int arr) {
		this.pid = pid;
		this.exec = exec;
		this.priority = priority;
		this.arr = arr;
	}
	public proc(int pid, int exec) {
		this.pid = pid;
		this.exec = exec;
		arr = 0;
		priority  = 0;
	}
	public proc(int pid, int exec, int priority) {
		this.pid = pid;
		this.exec = exec;
		this.priority = priority;
		arr = 0;
	}
}