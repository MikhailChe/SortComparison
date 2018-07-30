/*
 * Это основной класс.
 * Отсюда запускается и инициализируется вся программа.
 */
package sort;

/*
 * Импортируем нужные библиотеки
 */
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import acm.gui.IntField;
import acm.program.GraphicsProgram;

/*
 * Библиотека для расширения (extends)
 * собственных программ. При запуске создает
 * окно и холст. 
 */
public class Main extends GraphicsProgram {
	/*
	 * Описание класса Main. Этот класс расширяется классом GraphicsProgram, то
	 * есть наследует от него все функции, переменные и константы. В итоге
	 * приложение становится апплетом (как те, которые включают на
	 * веб-страницы).
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -8234321824857184551L;

	public static void main(String[] args) {
		/*
		 * Апплеты не являются самостоятельными программами до тех пор, пока не
		 * имеют функцию main. Эта функция и содержащийся внутри неё вызов new
		 * Main().start(args); позволяет "собрать" готовый jar файл и запускать
		 * его там, где есть java-машина, как отдельную программу, а не только
		 * как часть web-страницы.
		 * 
		 * Сам по себе main является первой запускаемой функцией
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		/*
		 * Данный набор функций пытается установить набор кнопок и полей в
		 * системный вид, то есть в Windows кнопки будут выглядеть как
		 * стандартные кнопки в Windows, в Mac OS кнопки будут выглядеть как
		 * стандартные для Mac OS и так далее.
		 */

		new Main().start(args);

	}

	@Override
	public void init() {
		/*
		 * Вторая в очереди запускаемая функция. Если быть точнее, то она
		 * вызывается при вызове new Main().start(args); В дальнейшем функция
		 * передает управление функции run()
		 */
		setSize(800, 600);
		/*
		 * Устанавливает изначальный размер окна
		 */
		setName("Сортировки © Черноскутов Михаил 2011");
		setTitle("Сортировки © Черноскутов Михаил 2011");
		/*
		 * Устанавливает имя и заголовок программы
		 */
		/*
		 * Далее добавляются элементы пользовательсокго интерфейсв
		 */
		add(new JLabel("Количество массивов:"), WEST);
		add(Nos, WEST);
		add(new JLabel("Размер массива:"), WEST);
		add(ArrSize, WEST);
		add(isSame, WEST);
		add(isAligned, WEST);
		isAligned.addActionListener((e) -> {
			if (isAligned.isSelected()) {
				UpSorted.setVisible(true);
			} else {
				UpSorted.setVisible(false);
			}
		});

		add(UpSorted, WEST);
		UpSorted.setVisible(false);
		add(new JButton("Подготовить"), WEST);
		add(new JLabel("Типы сортировки:"), WEST);
		add(myList, WEST);
		add(new JButton("Сортировать"), WEST);
		add(new JButton("Остановить"), WEST);
		add(new JLabel("Ползунок скорости"), WEST);
		add(PAUSETIME, WEST);
		addActionListeners();
	}

	@Override
	public void run() {
		/*
		 * Третяя по порядку вызова функция. Вызывается как правило после
		 * функции init()
		 */
		initArray();
		/*
		 * Вызов функции инициализации массивов
		 */
		initGraphic();
		/*
		 * Вызов функции инициализации графики по готовым массивам
		 */
		Timer t = new Timer(true);
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				redrawG();
			}
		}, 10, 10);
		/*
		 * Запускаем таймер, который каждые 500 милисекунд (0.5 секунд) будет
		 * перерисовывать изображения на холсте. За это отвечает фунция
		 * redrawG(). Это нужно например для того, чтобы при изменении размеров
		 * окна изображение всегда подстраивалось под его новые размеры. Как
		 * было сказано ранее, функция перерисовки будет запускаться в отдельном
		 * потоке.
		 */
	}

	public synchronized void initGraphic() {
		/*
		 * initGraph - Функция инициализации графики по готовому массиву
		 */
		removeAll();
		/*
		 * Для начала удаляем с холста существующую там графику
		 */
		for (int i = 0; i < vecs.size(); i++) {
			/*
			 * Для каждого из элементов вектора vecs делаем следующее
			 */
			vecs.get(i).initRects(i);
			/*
			 * Инициализируем даем каждому элементу проинициализировать свои
			 * прямоугольники с помощью функции initRects. В качестве параметра
			 * передается номер массива.
			 */
			for (int j = 0; j < vecs.get(i).Rects.size(); j++) {
				/*
				 * Для каждого из созданых прямоугольников мы делаем следующее:
				 */
				add(vecs.get(i).Rects.get(j));
				/*
				 * Добавляем на холст созданый прямоугольник
				 */
				add(vecs.get(i).Values.get(j));
				/*
				 * Добавим на холст надпись со значением, которая была создана
				 * функцией vecs.get(i).initRects(i); вместе с прямоугольниками
				 */
			}
			add(vecs.get(i).Name);
			/*
			 * Добавляем на холст надпись с названием сортировки
			 */
			add(vecs.get(i).SortTime);
			/*
			 * Добавляем на холст надпись с временем, потраченым на сортировку
			 */
		}
	}

	public static synchronized void redrawG() {
		/*
		 * redrawG() - Функция перерисовки графики
		 */
		for (int i = 0; i < vecs.size(); i++) {
			/*
			 * Для кажжого из элемента массива
			 */
			vecs.get(i).updateRects(i);
			/*
			 * Обновляем элементы графики с помощью функции updateRects(i) В
			 * качетсвет параметра передаем порядковый номер массива
			 */
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * actionPerformed - функция, которая запускается при возниновении
		 * события, то есть например нажатия какой-либо кнопки
		 */
		String cmd = e.getActionCommand();
		/*
		 * Присваиваем строковой переменной cmd название нажатой кнопки
		 */
		if (cmd.equals("Сортировать")) {
			/*
			 * Если нажата кнопка "сортировать", то запускаем функцию process();
			 */
			process();
		} else if (cmd.equals("Подготовить")) {
			/*
			 * Если "подготовить", то
			 */
			stopThreads();
			/*
			 * Останавливаем текущие активные потоки сортировок
			 */
			initArray();
			/*
			 * инициализируем массивы
			 */
			initGraphic();
			/*
			 * Инициализируем графику
			 */
		} else if (cmd.equals("Остановить")) {
			/*
			 * Если "остановить", то просто останавливаем рабочие потоки
			 */
			stopThreads();
		}
	}

	public static void pause(final long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void stopThreads() {
		/*
		 * Функция stopThreads просто переключает переменную stopAll в true
		 * Внутри каждого из бегущих процессов происходит проверка, является ли
		 * эта переменная true, если да, то потоки внутри себя заверщаются сами
		 */
		stopAll = true;
	}

	public void process() {
		/*
		 * process - одна из основных функций в этой программе. Эта функция
		 * запускает сам процесс сортировок, делает проверки
		 */
		redrawG();
		/*
		 * Сначала перерисовываем графику, чтобы пользователь видел обновленную
		 * версию графики.
		 */
		stopThreads();
		/*
		 * На всякий случай останавливаем потоки
		 */
		if (Threads.size() > 0) {
			for (Thread t : Threads)
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		Threads.clear();
		/*
		 * Удаляем их из вектора активных потоков
		 */

		/*
		 * Переключаем stopAll на true, чтобы сортировки, которые сейчас
		 * запустим не остановились сразу же
		 */
		if (vecs.firstElement().Numbers.size() != ArrSize.getValue()) {
			/*
			 * Если размер вектора Numbers первого элемента вектора vecs не
			 * равен заданному в GUI значению ArrSize, то
			 */
			initArray();
			/*
			 * Занаво переинициализируем массив, а значит размер массива
			 * изменяется
			 */
			initGraphic();
			/*
			 * Инициадизируем к массивам графику
			 */
		}
		/*
		 * Далее код, который определяет какие из типов сортировки запустить
		 */
		int[] SelectedSorts = myList.getSelectedIndices();
		/*
		 * Создаем массив SelectedSorts и присваеваем ему номера выбраных
		 * элементов массива
		 */
		if (SelectedSorts.length > Nos.getValue()) {
			/*
			 * Если длина массива SelectedSorts не равна заданному в GUI
			 * Значению Nos (количеству сортировок), то
			 */
			initArray();
			/*
			 * Занаво инициализируем массив
			 */
			initGraphic();
			/*
			 * Инициализируем графику
			 */
		}
		if (SelectedSorts.length == 0) {
			/*
			 * Если ни один элемент не выбран, то
			 */
			JOptionPane
					.showMessageDialog(null, "Выберите хотя бы один вид сортировки", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
			/*
			 * Показываем сообщение о том, что нужно выбрать хотя бы один вид
			 * сортировки
			 */
			return;
			/*
			 * Завершаем функцию process
			 */
		}
		stopAll = false;
		for (int i = 0; i < vecs.size(); i++) {
			/*
			 * Для каждого из элементов массива vecs делаем следующее
			 */
			if (SelectedSorts[i % SelectedSorts.length] == 0) {
				/*
				 * Если остаток от деления i на количество сортировок равно
				 * нулю, то
				 */
				Threads.add(new Thread(new BubbleSort(vecs.get(i))));
				/*
				 * Создаем новый поток сортировкой пузырьком.
				 */
			} else if (SelectedSorts[i % SelectedSorts.length] == 1) {
				/*
				 * Если еденице, то Selection сорт.
				 */
				Threads.add(new Thread(new SelectionSort(vecs.get(i))));
			} else if (SelectedSorts[i % SelectedSorts.length] == 2) {
				/*
				 * Если двойке - Merge сорт.
				 */
				Threads.add(new Thread(new MergeSort(vecs.get(i))));
			} else if (SelectedSorts[i % SelectedSorts.length] == 3) {
				Threads.add(new Thread(new ShellSort(vecs.get(i))));
			} else if (SelectedSorts[i % SelectedSorts.length] == 4) {
				Threads.add(new Thread(new InsertionSort(vecs.get(i))));
			}
		}
		for (Thread t : Threads) {
			t.start();
			/*
			 * Запускаем каждый из созданных потоков
			 */
		}
		redrawG();
		/*
		 * Перерисовываем графику
		 */
	}

	public synchronized void initArray() {
		/*
		 * initArray() - Функция инициализации массива Она является синхронной
		 * функцией, то есть в одно время может работать только один экземпляр
		 * данной функции, так как здесь происходят большие затратные работы с
		 * массивами, и если две функции запустятся одновременно, то данные
		 * могут быть неправильно записаны, что приведет к ошибкам
		 */
		if (myList.getSelectedIndices().length > Nos.getValue()) {
			/*
			 * Если количество выделеных сортировок больше занчения заданному в
			 * GUI, то мы приравниваем значение в GUI к количеству выбраных
			 * сортировок
			 */
			Nos.setValue(myList.getSelectedIndices().length);
		}
		if (vecs.size() != Nos.getValue()) {
			/*
			 * Если количество массивов для сортировки не равно количеству,
			 * указанному в GUI
			 */
			vecs.clear();
			/*
			 * Очищаем вектор vecs
			 */
			VecaRect.NOS = 0;
			/*
			 * Приравниваем переменную NOS к нолю (NOS - Number of Sorts -
			 * количество сортировок)
			 */
			for (int i = 0; i < Nos.getValue(); i++) {
				/*
				 * Добавляем новый VecaRect Nos.getValue() раз
				 */
				vecs.add(new VecaRect(this));
			}
		}
		Vector<Integer> Numbers = new Vector<>();
		/*
		 * Создаем новый вектор из целых чисел Numbers
		 */
		if (isSame.isSelected()) {
			/*
			 * Если поставлена галочка на элементе "одинаковые массивы"
			 */
			if (isAligned.isSelected()) {
				/*
				 * Если стоит галочка на элементе "отсортированые массивы"
				 */
				if (UpSorted.isSelected()) {
					/*
					 * Если стоит галочка на элементе "Отсортированы вверх"
					 */
					for (int i = 0; i < ArrSize.getValue(); i++) {
						/*
						 * Добавляем в вектор подряд ArrSize элементов, каждое
						 * равно i Пример: ArrSize = 4
						 * 
						 * i Action
						 * 
						 * 0 Numbers.add(0)
						 * 
						 * 1 Numbers.add(1)
						 * 
						 * 2 Numbers.add(2)
						 * 
						 * 3 Numbers.add(3)
						 */
						Numbers.add(i);
					}
				} else {
					for (int i = 0; i < ArrSize.getValue(); i++) {
						/*
						 * То же самое, только числа наоборот
						 */
						Numbers.add(ArrSize.getValue() - i);
					}
				}
			} else {
				/*
				 * То же самое, только числа рандомные
				 */
				for (int i = 0; i < ArrSize.getValue(); i++) {
					Numbers.add((int) (Math.random() * ArrSize.getValue()));
				}
			}
		}
		for (int i = 0; i < vecs.size(); i++) {
			/*
			 * Для каждого элемента массива vecs
			 */
			if (isSame.isSelected()) {
				/*
				 * Если стоит галочка на "Одинаковые массивы", то инициализируем
				 * каждый массив только что созданным Numbers
				 */
				vecs.get(i).initArray(Numbers);
			} else {
				/*
				 * Иначе передаем всё в руки функции initArray каждого элемента
				 */
				vecs.get(i).initArray(ArrSize.getValue(), isAligned.isSelected(), UpSorted.isSelected());
			}
			/*
			 * Снимаем графическое выделение с каждого элемента
			 */
			vecs.get(i).DeselectAll();
		}
	}

	public static boolean stopAll = false;
	// Переменая для остановки потоков
	public static JCheckBox isSame = new JCheckBox("Одинаковые массивы", true);
	// Галочка "одинаковые массивы"
	public static JCheckBox isAligned = new JCheckBox("Отсортировано", false);
	// Галочка "отсортировано"
	public static JCheckBox UpSorted = new JCheckBox("Сортировано вверх", true);
	// Галочка "Сортировано вверх"
	public static IntField ArrSize = new IntField(20, 3, 10000);
	// Поле размер массива
	public static IntField Nos = new IntField(1, 1, 100);
	// Поле количество массивов
	public static JScrollBar PAUSETIME = new JScrollBar(0, 0, 0, 0, 1000);
	// Поле времения задержки
	public static String[] data = { "Пузырек", "Выборка", "Слияние", "Шелл", "Вставками" };
	public static JList<String> myList = new JList<>(data);
	// Список сортировок
	public static Vector<VecaRect> vecs = new Vector<>();
	// вектор из элементов класса VecaRect
	public static Vector<Thread> Threads = new Vector<>();
	// вектор из потоков
}
