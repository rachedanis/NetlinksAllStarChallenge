package com.yalantis.guillotine.sample.utls;

import com.activeandroid.query.Select;
import java.util.List;

/**
 * Created by Anis on 01/01/15.
 */

public class Repository implements IRepository {

    public History getHistoryById(int id) {
        return new Select()
                .from(History.class)
                .where("historyId = ?", id)
                .executeSingle();
    }

    public List<History> getAll() {
        return new Select()
                .from(History.class)
                .execute();
    }
    public void AddHistory(History history){
        history.save();
    }
}
