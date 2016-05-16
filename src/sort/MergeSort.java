package sort;

import java.util.Vector;

public class MergeSort implements Runnable {

	public MergeSort(Main themain) {
		this.main = themain;
	}

	public MergeSort(Main themain, VecaRect vect) {
		this.main = themain;
		this.veca = vect;
		this.v = vect.Numbers;
	}

	public void MSort(Vector<Integer> v, int s, int e) {
		main.pause(Main.PAUSETIME.getValue() * multi);
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
		Vector<Integer> v1 = new Vector<Integer>();
		if (Main.stopAll == true) {
			return;
		}
		for (int i = s; i < m; i++) {
			v1.add(v.get(i).intValue());
			main.pause(Main.PAUSETIME.getValue() * multi/(m-s));
		}
		Vector<Integer> v2 = new Vector<Integer>();

		for (int i = m; i < e; i++) {
			v2.add(v.get(i).intValue());
			main.pause(Main.PAUSETIME.getValue() * multi/(e-m));
		}
		while ((v1.size() > 0) & (v2.size() > 0)) {
			veca.DeselectAll();
			if (Main.stopAll == true) {
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
			main.pause(Main.PAUSETIME.getValue() * multi);
		}
		while (v1.size() > 0) {
			veca.DeselectAll();
			if (Main.stopAll == true) {
				return;
			}
			veca.SelectElement(s);
			v.set(s, v1.firstElement());
			v1.remove(v1.firstElement());
			s++;
			main.pause(Main.PAUSETIME.getValue() * multi);
		}
		while (v2.size() > 0) {
			veca.DeselectAll();
			if (Main.stopAll == true) {
				return;
			}
			veca.SelectElement(s);
			v.set(s, v2.firstElement());
			v2.remove(v2.firstElement());
			s++;
			main.pause(Main.PAUSETIME.getValue() * multi);
		}

	}

	@Override
	public void run() {
		veca.setName(SortName);
		/*Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				main.redrawG();
			}
		}, (long) 1, (long) 1);*/
		veca.clearTime();
		double thetime = System.currentTimeMillis();
		MSort(v, 0, v.size());
		veca.setTime("" + (System.currentTimeMillis() - thetime) / 1000);
		veca.DeselectAll();
		main.redrawG();
		//t.cancel();
	}

	Main main;
	public VecaRect veca;
	public String SortName = "Сортировка слиянием";
	public Vector<Integer> v;
	public final int multi = 1;
}
