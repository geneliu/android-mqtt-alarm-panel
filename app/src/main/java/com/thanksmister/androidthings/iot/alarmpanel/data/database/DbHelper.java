/*
 * <!--
 *   ~ Copyright (c) 2017. ThanksMister LLC
 *   ~
 *   ~ Licensed under the Apache License, Version 2.0 (the "License");
 *   ~ you may not use this file except in compliance with the License. 
 *   ~ You may obtain a copy of the License at
 *   ~
 *   ~ http://www.apache.org/licenses/LICENSE-2.0
 *   ~
 *   ~ Unless required by applicable law or agreed to in writing, software distributed 
 *   ~ under the License is distributed on an "AS IS" BASIS, 
 *   ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *   ~ See the License for the specific language governing permissions and 
 *   ~ limitations under the License.
 *   -->
 */

package com.thanksmister.androidthings.iot.alarmpanel.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thanksmister.androidthings.iot.alarmpanel.data.database.model.ComponentModel;
import com.thanksmister.androidthings.iot.alarmpanel.data.database.model.SubscriptionModel;
import com.thanksmister.androidthings.iot.alarmpanel.data.database.model.WeatherModel;

public class DbHelper extends SQLiteOpenHelper {

    //instance for singleton
    private static DbHelper dbInstance;
    private static Context dbContext;

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mqtt_alarm_panel.db";

    //Singleton getInstance class
    public static synchronized DbHelper getInstance(Context context) {
        if(dbInstance == null) {
            dbInstance = new DbHelper(context);
        }
        dbContext = context;
        return dbInstance;
    }

    //private for singleton contract
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
        db.execSQL(WeatherModel.CREATE_TABLE_STATEMENT);
        db.execSQL(ComponentModel.CREATE_TABLE_STATEMENT);
        db.execSQL(SubscriptionModel.CREATE_TABLE_STATEMENT);

        /*db.insert(MessageModel.TABLE_NAME, null, 
                MessageModel.createBuilder(
                        AlarmUtils.TYPE_ALARM_PANEL,
                        dbContext.getString(R.string.text_alarm_publish_armed_away),
                        AlarmUtils.MESSAGE_TYPE_PUBLISH,
                        Constants.TOPIC_PUBLISH, 
                        Constants.PUBLISH_ARMED_AWAY)
                        .build());*/
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        if (oldVersion < newVersion) {
            db.execSQL(CREATE_DASHBOARD);
        }
        */
    }
    
    public void onDelete() {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(WeatherModel.DELETE_TABLE_STATEMENT);
        db.execSQL(ComponentModel.DELETE_TABLE_STATEMENT);
        db.execSQL(SubscriptionModel.DELETE_TABLE_STATEMENT);
    }
}