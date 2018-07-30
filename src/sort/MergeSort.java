package sort;

import java.util.Vector;

public class MergeSort extends AbstractSort {

	public MergeSort(VecaRect vect) {
		super(vect);
	}

	public void MSort(Vector<Integer> v, int s, int e) {
		Main.pause(Main.PAUSETIME.getValue() * multi);
		if (Main.stopAll == true) {
			return;
		}
		if ((e - s) > 1) {
			MSort(v, s, (e - s) / 2 + s);
			MSort(v, (e - s) / 2 + s, e);
			Merge(v, s, (e - s) / 2 + s, e);
		}
	}

	public void Merge(Vector<Integer> v, int s, int m, int e) {
		Vector<Integer> v1 = new Vector<>();
		if (Main.stopAll == true) {
			return;
		}
		for (int i = s; i < m; i++) {
			v1.add(v.get(i).intValue());
			Main.pause(Main.PAUSETIME.getValue() * multi / (m - s));
		}
		Vector<Integer> v2 = new Vector<>();

		for (int i = m; i < e; i++) {
			v2.add(v.get(i).intValue());
			Main.pause(Main.PAUSETIME.getValue() * multi / (e - m));
		}
		while ((v1.size() > 0) & (v2.size() > 0)) {

			if (Main.stopAll == true) {
				veca.DeselectAll();
				return;
			}
			veca.SelectElement(s);
			if (v1.firstElement() < v2.firstElement()) {
				v.set(s, v1.firstElement());
				v1.remove(v1.firstElement());
			} else {
				v.set(s, v2.firstElement());
				v2.remove(v2.firstElement());
			}
			s++;
			Main.pause(Main.PAUSETIME.getValue() * multi);
		}
		veca.DeselectAll();
		while (v1.size() > 0) {

			if (Main.stopAll == true) {
				veca.DeselectAll();
				return;
			}
			veca.SelectElement(s);
			v.set(s, v1.firstElement());
			v1.remove(v1.firstElement());
			s++;
			Main.pause(Main.PAUSETIME.getValue() * multi);
		}
		veca.DeselectAll();
		while (v2.size() > 0) {

			if (Main.stopAll == true) {
				veca.DeselectAll();
				return;
			}
			veca.SelectElement(s);
			v.set(s, v2.firstElement());
			v2.remove(v2.firstElement());
			s++;
			Main.pause(Main.PAUSETIME.getValue() * multi);
		}
		veca.DeselectAll();
	}

	public final int multi = 1;

	@Override
	void SetName() {
		veca.setName("Сортировка слиянием");
	}

	@Override
	public void Sort(Vector<Integer> vect) {
		MSort(vect, 0, v.size());
	}

}
