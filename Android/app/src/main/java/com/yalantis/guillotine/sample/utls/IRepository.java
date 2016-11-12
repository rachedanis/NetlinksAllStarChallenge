package com.yalantis.guillotine.sample.utls;

import java.util.List;

/**
 * Created by Anis on 01/01/15.
 */

public interface IRepository {
    public History getHistoryById(int id);
    public List<History> getAll();
    public void AddHistory(History history);
}
