package com.noorifytech.maha.core.usecase.factory

import com.noorifytech.maha.data.dao.DatabaseDao
import com.noorifytech.maha.data.dao.impl.H2DatabaseDao
import com.noorifytech.maha.data.dao.impl.db.impl.H2Database
import com.noorifytech.maha.data.dao.mapper.DatabaseDaoMapper

object DaoFactory {
    fun getDatabaseDao(): DatabaseDao {
        return H2DatabaseDao(H2Database, DatabaseDaoMapper)
    }
}