/**
 * Общий интерфейс для слушателей классов - слушателей
 */

package programmingLanguagesJava.laboratories.GUI.controllers.project;
// zB слушатель - уведомления, звонок. Когда что-то записал, сделал - уведомляет (вспомни уведомления)
public interface Observer {
    /**
     * Метод, который запускает слушателя для нашего класса
     */
    void listen();
}
