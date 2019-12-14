package com.example.demo.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.HiveDataDto;
import com.example.demo.dto.HiveTableStatDto;


@Component
public class HiveDataService {

	@Autowired
	@Qualifier("jdbcHive")
	private JdbcTemplate jdbcTemplate;
	
	
	public List<HiveDataDto> getDetails(String databaseName) {
		String query ="select * from TBLS inner join DBS where TBLS.DB_ID=DBS.DB_ID;";
		Object[] r = new Object[]{};
		if(databaseName != null) {
			query ="select * from TBLS inner join DBS where TBLS.DB_ID=DBS.DB_ID and DBS.NAME=?";
			r = new Object[]{databaseName};
		}
		List<HiveDataDto> ls = jdbcTemplate.query(query,
				new HiveRowMapper(), r);
		return ls;
	}
	
	class HiveRowMapper implements RowMapper<HiveDataDto>
	{
		@Override
		public HiveDataDto mapRow(ResultSet rs, int rowNo) throws SQLException {
			HiveDataDto dto = new HiveDataDto();
			dto.setDatabaseName(rs.getString("NAME"));
			dto.setDbId(new Integer(rs.getInt("DB_ID")).toString());
			dto.setDbLocationUri(rs.getString("DB_LOCATION_URI"));
			dto.setTableName(rs.getString("TBL_NAME"));
			dto.setTableId(new Integer(rs.getInt("TBL_ID")).toString());
			return dto;
		}

	}
	
	public List<HiveTableStatDto> getTableStats(String tableId) {
		String query ="select * from TABLE_PARAMS  where TBL_ID=?;";
		Object[] r = new Object[]{new Long(tableId)};
		List<HiveTableStatDto> ls = jdbcTemplate.query(query,
				new HiveStatRowMapper(), r);
		return ls;
	}
	
	class HiveStatRowMapper implements RowMapper<HiveTableStatDto>
	{
		@Override
		public HiveTableStatDto mapRow(ResultSet rs, int rowNo) throws SQLException {
			HiveTableStatDto dto = new HiveTableStatDto();
			dto.setPARAM_KEY(rs.getString("PARAM_KEY"));
			dto.setPARAM_VALUE(rs.getString("PARAM_VALUE"));
			return dto;
		}

	}
}

