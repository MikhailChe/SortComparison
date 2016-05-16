package sort;

import java.util.Vector;

public class InsertionSort extends BubbleSort {

	public InsertionSort(Main themain) {
		super(themain);
		// TODO Auto-generated constructor stub
	}

	public InsertionSort(Main themain, VecaRect vect) {
		super(themain, vect);
		// TODO Auto-generated constructor stub
	}

	public synchronized void SetName() {
		veca.setName("Сортировка вставками");
	}

	public synchronized void Sort(Vector<Integer> vect) {
		int i, j;
		for (i = 1; i < vect.size(); i++) {
			veca.DeselectAll();
			if (Main.stopAll == true) {
				/*
				 * Если переменная stopAll в объекте класса Main является true,
				 * то прекращаем сортировку
				 */
				return;
			}
			veca.SelectElement(i);
			main.pause(Main.PAUSETIME.getValue());
			j = i;
			while (j > 0 && vect.get(j - 1) > vect.get(j)) {
				veca.SelectElement(i);
				veca.SelectElement(j);
				veca.SelectElement(j - 1);
				main.pause(Main.PAUSETIME.getValue());
				Swap(vect, j, j - 1);
				veca.DeselectAll();
				if (Main.stopAll == true) {
					/*
					 * Если переменная stopAll в объекте класса Main является
					 * true, то прекращаем сортировку
					 */
					return;
				}
				j--;
			}
		}
	}
}
