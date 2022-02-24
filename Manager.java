import java.util.*;
/*
Кроме классов для описания задач, вам нужно реализовать класс для объекта-менеджера. Он будет запускаться на старте
программы и управлять всеми задачами. В нём должны быть реализованы следующие функции:
        1.	Возможность хранить задачи всех типов. Для этого вам нужно выбрать подходящую коллекцию.
        2.	Методы для каждого из типа задач(Задача/Эпик/Подзадача):
            1.	Получение списка всех задач.
            2.	Удаление всех задач.
            3.	Получение по идентификатору.
            4.	Создание. Сам объект должен передаваться в качестве параметра.
            5.	Обновление. Новая версия объекта с верным идентификатором передаются в виде параметра.
            6.	Удаление по идентификатору.
        3.	Дополнительные методы:
            1.	Получение списка всех подзадач определённого эпика.
        4.	Управление статусами осуществляется по следующему правилу:
            1.	Менеджер сам не выбирает статус для задачи. Информация о нём приходит менеджеру вместе с информацией о самой
            задаче. По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.
            2.	Для эпиков:
            3.	если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
            4.	если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
            5.	во всех остальных случаях статус должен быть IN_PROGRESS.
*/

public class Manager {

    private Map<Integer, Task> taskMap;
    private Map<Integer, Epic> epicMap;
    private Map<Integer, Subtask> subtaskMap;

    public Manager() {
        taskMap = new HashMap<>();
        epicMap = new HashMap<>();
        subtaskMap = new HashMap<>();
    }

    // Создание. Сам объект должен передаваться в качестве параметра:
    public void newEpic(Epic epic) {
        epicMap.put(epic.getId(), epic);
    }

    public void newTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void newSubtask(Subtask subtask) {
        subtaskMap.put(subtask.getId(), subtask);
    }

    public Map<Integer, Epic> getEpicMap() {
        return epicMap;
    }

    public Map<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public Map<Integer, Subtask> getSubtaskMap() {
        return subtaskMap;
    }

    // Получение списка всех задач:
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            tasks.add(entry.getValue());
        }
        return tasks;
    }

    public List<Epic> getAllEpics() {
        List<Epic> epics = new ArrayList<>();
        for (Map.Entry<Integer, Epic> entry : epicMap.entrySet()) {
            epics.add(entry.getValue());
        }
        return epics;
    }

    public List<Subtask> getAllSubtasks() {
        List<Subtask> subtasks = new ArrayList<>();
        for (Map.Entry<Integer, Subtask> entry : subtaskMap.entrySet()) {
            subtasks.add(entry.getValue());
        }
        return subtasks;
    }

    // Удаление всех задач:
    public void clearTasks() {
        taskMap.clear();
    }

    public void clearEpics() {
        epicMap.clear();
    }

    public void clearSubtasks() {
        subtaskMap.clear();
    }

    // Получение по идентификатору:
    public Task getTaskById(int id) {
        return taskMap.get(id);
    }

    public Epic getEpicById(int id) {
        return epicMap.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtaskMap.get(id);
    }

    // Обновление. Новая версия объекта с верным идентификатором передаются в виде параметра:
    public void updateTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epicMap.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtaskMap.put(subtask.getId(), subtask);
    }

    // Удаление по идентификатору:
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    public void deleteEpic(int id) {
        epicMap.remove(id);
    }

    public void deleteSubtask(int id) {
        for (Epic epic : epicMap.values()) {
            epic.getSubtaskMap().remove(id);
        }
    }

    // Получение списка всех подзадач определённого эпика:
    public List<Subtask> getAllSubtaskByEpic(Epic epic) {
        List<Subtask> subtaskList = new ArrayList<>();
        for (Subtask subtask : epic.getSubtaskMap().values()) {
            subtaskList.add(subtask);
        }
        return subtaskList;
    }

    // Выводим в консоль все таски для проверки:
    public void printAllTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println("id: " + task.getId() + ". " + task.getName());
        }
    }

    // Выводим в консоль все епики с подзадачами для проверки:
    public void printAllEpicsAndSubtasks(List<Epic> epics) {
        for (Epic epic : epics) {
            System.out.println("id: " + epic.getId() + ". " + epic.getName());
            for (Subtask subtask : getAllSubtaskByEpic(epic)) {
                System.out.println("    id: " + subtask.getId() + ". " + subtask.getName());
            }
        }
    }
}
