package sort;

import java.util.Vector;

public class SelectionSort extends AbstractSort {

	public SelectionSort(VecaRect vect) {
		super(vect);
	}

	@Override
	public synchronized void Sort(Vector<Integer> vec) {
		int n = vec.size();
		for (int lh = 0; lh < n; lh++) {
			veca.DeselectAll();
			if (Main.stopAll == true) {
				return;
			}
			veca.SelectElement(lh);
			int rh = lh;
			for (int i = lh + 1; i < n; i++) {
				veca.DeselectAll();
				if (Main.stopAll == true) {
					return;
				}
				veca.SelectElement(lh);
				veca.SelectElement(i);
				veca.SelectElement(rh);
				Main.pause(Main.PAUSETIME.getValue());
				if (vec.get(i) < vec.get(rh))
					rh = i;
			}
			Swap(vec, lh, rh);
		}
	}

	@Override
	void SetName() {
		veca.setName("Сортировка выборкой");

	}

}
