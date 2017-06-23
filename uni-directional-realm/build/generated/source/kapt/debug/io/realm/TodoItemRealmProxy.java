package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TodoItemRealmProxy extends com.acme.realmudi.model.TodoItem
    implements RealmObjectProxy, TodoItemRealmProxyInterface {

    static final class TodoItemColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long createdDateIndex;
        public long textIndex;
        public long isSelectedIndex;

        TodoItemColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(4);
            this.idIndex = getValidColumnIndex(path, table, "TodoItem", "id");
            indicesMap.put("id", this.idIndex);
            this.createdDateIndex = getValidColumnIndex(path, table, "TodoItem", "createdDate");
            indicesMap.put("createdDate", this.createdDateIndex);
            this.textIndex = getValidColumnIndex(path, table, "TodoItem", "text");
            indicesMap.put("text", this.textIndex);
            this.isSelectedIndex = getValidColumnIndex(path, table, "TodoItem", "isSelected");
            indicesMap.put("isSelected", this.isSelectedIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final TodoItemColumnInfo otherInfo = (TodoItemColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.createdDateIndex = otherInfo.createdDateIndex;
            this.textIndex = otherInfo.textIndex;
            this.isSelectedIndex = otherInfo.isSelectedIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final TodoItemColumnInfo clone() {
            return (TodoItemColumnInfo) super.clone();
        }

    }
    private TodoItemColumnInfo columnInfo;
    private ProxyState<com.acme.realmudi.model.TodoItem> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("createdDate");
        fieldNames.add("text");
        fieldNames.add("isSelected");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TodoItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TodoItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.acme.realmudi.model.TodoItem>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public Date realmGet$createdDate() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.createdDateIndex);
    }

    public void realmSet$createdDate(Date value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createdDate' to null.");
            }
            row.getTable().setDate(columnInfo.createdDateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'createdDate' to null.");
        }
        proxyState.getRow$realm().setDate(columnInfo.createdDateIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$text() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.textIndex);
    }

    public void realmSet$text(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'text' to null.");
            }
            row.getTable().setString(columnInfo.textIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'text' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.textIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$isSelected() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isSelectedIndex);
    }

    public void realmSet$isSelected(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isSelectedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isSelectedIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("TodoItem")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("TodoItem");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("createdDate", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("text", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("isSelected", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("TodoItem");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_TodoItem")) {
            Table table = sharedRealm.getTable("class_TodoItem");
            table.addColumn(RealmFieldType.STRING, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.DATE, "createdDate", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "text", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "isSelected", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_TodoItem");
    }

    public static TodoItemColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_TodoItem")) {
            Table table = sharedRealm.getTable("class_TodoItem");
            final long columnCount = table.getColumnCount();
            if (columnCount != 4) {
                if (columnCount < 4) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TodoItemColumnInfo columnInfo = new TodoItemColumnInfo(sharedRealm.getPath(), table);

            if (!table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. @PrimaryKey was added.");
            } else {
                if (table.getPrimaryKey() != columnInfo.idIndex) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field id");
                }
            }

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex) && table.findFirstNull(columnInfo.idIndex) != Table.NO_MATCH) {
                throw new IllegalStateException("Cannot migrate an object with null value in field 'id'. Either maintain the same type for primary key field 'id', or remove the object with null value before migration.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("createdDate")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'createdDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("createdDate") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'createdDate' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.createdDateIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'createdDate' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'createdDate' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("text")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'text' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("text") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'text' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.textIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'text' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'text' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isSelected")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isSelected' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isSelected") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isSelected' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.isSelectedIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isSelected' does support null values in the existing Realm file. Use corresponding boxed type for field 'isSelected' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'TodoItem' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TodoItem";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.acme.realmudi.model.TodoItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.acme.realmudi.model.TodoItem obj = null;
        if (update) {
            Table table = realm.getTable(com.acme.realmudi.model.TodoItem.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.acme.realmudi.model.TodoItem.class), false, Collections.<String> emptyList());
                    obj = new io.realm.TodoItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.TodoItemRealmProxy) realm.createObjectInternal(com.acme.realmudi.model.TodoItem.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.TodoItemRealmProxy) realm.createObjectInternal(com.acme.realmudi.model.TodoItem.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("createdDate")) {
            if (json.isNull("createdDate")) {
                ((TodoItemRealmProxyInterface) obj).realmSet$createdDate(null);
            } else {
                Object timestamp = json.get("createdDate");
                if (timestamp instanceof String) {
                    ((TodoItemRealmProxyInterface) obj).realmSet$createdDate(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((TodoItemRealmProxyInterface) obj).realmSet$createdDate(new Date(json.getLong("createdDate")));
                }
            }
        }
        if (json.has("text")) {
            if (json.isNull("text")) {
                ((TodoItemRealmProxyInterface) obj).realmSet$text(null);
            } else {
                ((TodoItemRealmProxyInterface) obj).realmSet$text((String) json.getString("text"));
            }
        }
        if (json.has("isSelected")) {
            if (json.isNull("isSelected")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isSelected' to null.");
            } else {
                ((TodoItemRealmProxyInterface) obj).realmSet$isSelected((boolean) json.getBoolean("isSelected"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.acme.realmudi.model.TodoItem createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.acme.realmudi.model.TodoItem obj = new com.acme.realmudi.model.TodoItem();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TodoItemRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((TodoItemRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("createdDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TodoItemRealmProxyInterface) obj).realmSet$createdDate(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((TodoItemRealmProxyInterface) obj).realmSet$createdDate(new Date(timestamp));
                    }
                } else {
                    ((TodoItemRealmProxyInterface) obj).realmSet$createdDate(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("text")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TodoItemRealmProxyInterface) obj).realmSet$text(null);
                } else {
                    ((TodoItemRealmProxyInterface) obj).realmSet$text((String) reader.nextString());
                }
            } else if (name.equals("isSelected")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isSelected' to null.");
                } else {
                    ((TodoItemRealmProxyInterface) obj).realmSet$isSelected((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.acme.realmudi.model.TodoItem copyOrUpdate(Realm realm, com.acme.realmudi.model.TodoItem object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.acme.realmudi.model.TodoItem) cachedRealmObject;
        } else {
            com.acme.realmudi.model.TodoItem realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.acme.realmudi.model.TodoItem.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstString(pkColumnIndex, ((TodoItemRealmProxyInterface) object).realmGet$id());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.acme.realmudi.model.TodoItem.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.TodoItemRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.acme.realmudi.model.TodoItem copy(Realm realm, com.acme.realmudi.model.TodoItem newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.acme.realmudi.model.TodoItem) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.acme.realmudi.model.TodoItem realmObject = realm.createObjectInternal(com.acme.realmudi.model.TodoItem.class, ((TodoItemRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((TodoItemRealmProxyInterface) realmObject).realmSet$createdDate(((TodoItemRealmProxyInterface) newObject).realmGet$createdDate());
            ((TodoItemRealmProxyInterface) realmObject).realmSet$text(((TodoItemRealmProxyInterface) newObject).realmGet$text());
            ((TodoItemRealmProxyInterface) realmObject).realmSet$isSelected(((TodoItemRealmProxyInterface) newObject).realmGet$isSelected());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.acme.realmudi.model.TodoItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.acme.realmudi.model.TodoItem.class);
        long tableNativePtr = table.getNativeTablePointer();
        TodoItemColumnInfo columnInfo = (TodoItemColumnInfo) realm.schema.getColumnInfo(com.acme.realmudi.model.TodoItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((TodoItemRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$createdDate = ((TodoItemRealmProxyInterface)object).realmGet$createdDate();
        if (realmGet$createdDate != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.createdDateIndex, rowIndex, realmGet$createdDate.getTime(), false);
        }
        String realmGet$text = ((TodoItemRealmProxyInterface)object).realmGet$text();
        if (realmGet$text != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.textIndex, rowIndex, realmGet$text, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((TodoItemRealmProxyInterface)object).realmGet$isSelected(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.acme.realmudi.model.TodoItem.class);
        long tableNativePtr = table.getNativeTablePointer();
        TodoItemColumnInfo columnInfo = (TodoItemColumnInfo) realm.schema.getColumnInfo(com.acme.realmudi.model.TodoItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.acme.realmudi.model.TodoItem object = null;
        while (objects.hasNext()) {
            object = (com.acme.realmudi.model.TodoItem) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((TodoItemRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$createdDate = ((TodoItemRealmProxyInterface)object).realmGet$createdDate();
                if (realmGet$createdDate != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.createdDateIndex, rowIndex, realmGet$createdDate.getTime(), false);
                }
                String realmGet$text = ((TodoItemRealmProxyInterface)object).realmGet$text();
                if (realmGet$text != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.textIndex, rowIndex, realmGet$text, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((TodoItemRealmProxyInterface)object).realmGet$isSelected(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.acme.realmudi.model.TodoItem object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.acme.realmudi.model.TodoItem.class);
        long tableNativePtr = table.getNativeTablePointer();
        TodoItemColumnInfo columnInfo = (TodoItemColumnInfo) realm.schema.getColumnInfo(com.acme.realmudi.model.TodoItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((TodoItemRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$createdDate = ((TodoItemRealmProxyInterface)object).realmGet$createdDate();
        if (realmGet$createdDate != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.createdDateIndex, rowIndex, realmGet$createdDate.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.createdDateIndex, rowIndex, false);
        }
        String realmGet$text = ((TodoItemRealmProxyInterface)object).realmGet$text();
        if (realmGet$text != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.textIndex, rowIndex, realmGet$text, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.textIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((TodoItemRealmProxyInterface)object).realmGet$isSelected(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.acme.realmudi.model.TodoItem.class);
        long tableNativePtr = table.getNativeTablePointer();
        TodoItemColumnInfo columnInfo = (TodoItemColumnInfo) realm.schema.getColumnInfo(com.acme.realmudi.model.TodoItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.acme.realmudi.model.TodoItem object = null;
        while (objects.hasNext()) {
            object = (com.acme.realmudi.model.TodoItem) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((TodoItemRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$createdDate = ((TodoItemRealmProxyInterface)object).realmGet$createdDate();
                if (realmGet$createdDate != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.createdDateIndex, rowIndex, realmGet$createdDate.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.createdDateIndex, rowIndex, false);
                }
                String realmGet$text = ((TodoItemRealmProxyInterface)object).realmGet$text();
                if (realmGet$text != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.textIndex, rowIndex, realmGet$text, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.textIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isSelectedIndex, rowIndex, ((TodoItemRealmProxyInterface)object).realmGet$isSelected(), false);
            }
        }
    }

    public static com.acme.realmudi.model.TodoItem createDetachedCopy(com.acme.realmudi.model.TodoItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.acme.realmudi.model.TodoItem unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.acme.realmudi.model.TodoItem)cachedObject.object;
            } else {
                unmanagedObject = (com.acme.realmudi.model.TodoItem)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.acme.realmudi.model.TodoItem();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((TodoItemRealmProxyInterface) unmanagedObject).realmSet$id(((TodoItemRealmProxyInterface) realmObject).realmGet$id());
        ((TodoItemRealmProxyInterface) unmanagedObject).realmSet$createdDate(((TodoItemRealmProxyInterface) realmObject).realmGet$createdDate());
        ((TodoItemRealmProxyInterface) unmanagedObject).realmSet$text(((TodoItemRealmProxyInterface) realmObject).realmGet$text());
        ((TodoItemRealmProxyInterface) unmanagedObject).realmSet$isSelected(((TodoItemRealmProxyInterface) realmObject).realmGet$isSelected());
        return unmanagedObject;
    }

    static com.acme.realmudi.model.TodoItem update(Realm realm, com.acme.realmudi.model.TodoItem realmObject, com.acme.realmudi.model.TodoItem newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((TodoItemRealmProxyInterface) realmObject).realmSet$createdDate(((TodoItemRealmProxyInterface) newObject).realmGet$createdDate());
        ((TodoItemRealmProxyInterface) realmObject).realmSet$text(((TodoItemRealmProxyInterface) newObject).realmGet$text());
        ((TodoItemRealmProxyInterface) realmObject).realmSet$isSelected(((TodoItemRealmProxyInterface) newObject).realmGet$isSelected());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TodoItem = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{createdDate:");
        stringBuilder.append(realmGet$createdDate());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{text:");
        stringBuilder.append(realmGet$text());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isSelected:");
        stringBuilder.append(realmGet$isSelected());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemRealmProxy aTodoItem = (TodoItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTodoItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTodoItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTodoItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
