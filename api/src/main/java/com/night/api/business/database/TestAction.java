package com.night.api.business.database;

import com.night.model.wrapper.database.TestWrapper;

import java.util.List;

public interface TestAction {
    List<TestWrapper> getTestWrapperList();

    void insertIntoTest(List<TestWrapper> wrapperList);
}
