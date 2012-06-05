/*import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class HelloSwt {
    public static void main(String[] args) {
    //Создаем объект Display для связи SWT
    //с дисплеем операционной системы
        Display display = new Display();
        //Создаем окно программы
        Shell shell = new Shell(display);
        shell.setText("SWT Hello");
        shell.setSize(200, 100);
        shell.open();
        //Обработка закрытия окна
        while (!shell.isDisposed()) {
        if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        //Ресурсы операционной системы
        //должны быть освобождены 
        display.dispose();
    }
}*/
