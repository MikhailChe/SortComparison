package sort;

import java.util.Vector;

public class ShellSort extends BubbleSort {

	public ShellSort(Main themain) {
		super(themain);
		// TODO Auto-generated constructor stub
	}

	public ShellSort(Main themain, VecaRect vect) {
		super(themain, vect);
		// TODO Auto-generated constructor stub
	}

	public synchronized void Sort(Vector<Integer> vect) {
		int inc = Math.round(vect.size() / 2);
		while (inc > 0) {
			for (int i = inc; i < vect.size(); i++) {
				veca.DeselectAll();
				if (Main.stopAll == true) {
					return;
				}
				veca.SelectElement(i);
				int temp = vect.get(i);
				int j = i;
				while (j >= inc && vect.get(j - inc) > temp) {
					veca.DeselectAll();
					if (Main.stopAll == true) {
							return;
					}
					veca.SelectElement(i);
					veca.SelectElement(j);
					veca.SelectElement(j-inc);
					main.pause(Main.PAUSETIME.getValue());
					Swap(vect, j, j-inc);
					j = j - inc;
				}
			}
			inc = (int) Math.round(inc / 2.2);
		}
	}

	public synchronized void SetName() {
		veca.setName("—ортировка Ўелла");
	}
}
