package sort;

import java.util.Vector;

public class ShellSort extends AbstractSort {

	public ShellSort(VecaRect vect) {
		super(vect);
	}

	@Override
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
					veca.SelectElement(j - inc);
					Main.pause(Main.PAUSETIME.getValue());
					Swap(vect, j, j - inc);
					j = j - inc;
				}
			}
			inc = (int) Math.round(inc / 2.2);
		}
	}

	@Override
	public synchronized void SetName() {
		veca.setName("Cортировка Шелла");
	}
}
