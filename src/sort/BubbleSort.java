package sort;

import java.util.Vector;

public class BubbleSort extends AbstractSort {

	public BubbleSort(VecaRect vect) {
		super(vect);
	}

	@Override
	public synchronized void Sort(Vector<Integer> vect) {
		/*
		 * Функция начала сортировки. В качетве аргумента принимает вектор из
		 * целых чисел.
		 * 
		 * Далее идет простой алгоритм пузырьковой сортировки.
		 */
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 1; i < vect.size(); i++) {
				veca.DeselectAll();
				/*
				 * Здесь снимаем графическое выделение со всех элементов
				 */
				if (Main.stopAll == true) {
					/*
					 * Если переменная stopAll в объекте класса Main является
					 * true, то прекращаем сортировку
					 */
					return;
				}
				veca.SelectElement(i);
				/*
				 * Создаем графическое выделение i элемента
				 */
				Main.pause(Main.PAUSETIME.getValue());
				/*
				 * Делаем задержку на Main.PAUSETIME милисекунд
				 */
				if (vect.get(i - 1) > vect.get(i)) {
					veca.DeselectAll();
					/*
					 * Снимаем выделение.
					 */
					veca.SelectElement(i - 1);
					veca.SelectElement(i);
					/*
					 * Ставим выделение на i элемент
					 */
					Swap(vect, i - 1, i);
					/*
					 * Вызываем функцию Swap, которая меняет элементы местами
					 */
					swapped = true;
				}
			}
		} while (swapped == true);
	}

	@Override
	public synchronized void SetName() {
		veca.setName("Сортировка пузырьками");
	}

}