package lab.lab1.presenters;

import java.util.List;

public interface CallBack {
    interface DepartmentCallBack {
        void onDepartmentLoad(List<String> data);
    }

    interface GroupCallBack {
        void onGroupLoad(List<String> data);
    }
}
