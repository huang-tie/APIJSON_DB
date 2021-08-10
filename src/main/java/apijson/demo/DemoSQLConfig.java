/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo;

import com.alibaba.fastjson.annotation.JSONField;

import apijson.framework.APIJSONSQLConfig;


/**SQL配置
 * TiDB 用法和 MySQL 一致
 * @author Lemon
 */
public class DemoSQLConfig extends APIJSONSQLConfig {

	static {
		DEFAULT_DATABASE = DATABASE_MYSQL;  // TODO 默认数据库类型，改成你自己的
		DEFAULT_SCHEMA = "sys";  // TODO 默认模式名，改成你自己的，默认情况是 MySQL: sys, PostgreSQL: public, SQL Server: dbo, Oracle: 

		// 表名和数据库不一致的，需要配置映射关系。只使用 APIJSONORM 时才需要；
		// 如果用了 apijson-framework 且调用了 APIJSONApplication.init
		// (间接调用 DemoVerifier.init 方法读取数据库 Access 表来替代手动输入配置)，则不需要。
		// 但如果 Access 这张表的对外表名与数据库实际表名不一致，仍然需要这里注册。例如
		//		TABLE_KEY_MAP.put(Access.class.getSimpleName(), "access");

		//表名映射，隐藏真实表名，对安全要求很高的表可以这么做
		TABLE_KEY_MAP.put("User", "apijson_user");
		TABLE_KEY_MAP.put("Privacy", "apijson_privacy");
	}

	@Override
	public String getDBVersion() {
		return "5.7.22";  // "8.0.11";  // TODO 改成你自己的 MySQL 或 PostgreSQL 数据库版本号  // MYSQL 8 和 7 使用的 JDBC 配置不一样
	}
	
	@JSONField(serialize = false)  // 不在日志打印 账号/密码 等敏感信息
	@Override
	public String getDBUri() {
		return "jdbc:mysql://localhost:3306"; // TODO 改成你自己的，TiDB 可以当成 MySQL 使用，默认端口为 4000
	}
	
	@JSONField(serialize = false)  // 不在日志打印 账号/密码 等敏感信息
	@Override
	public String getDBAccount() {
		return "root";  // TODO 改成你自己的
	}
	
	@JSONField(serialize = false)  // 不在日志打印 账号/密码 等敏感信息
	@Override
	public String getDBPassword() {
		return "apijson";  // TODO 改成你自己的，TiDB 可以当成 MySQL 使用， 默认密码为空字符串 ""
	}

}
