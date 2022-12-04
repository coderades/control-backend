package com.control.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		if (!"".equals(TenantDataSourceProvider.TenantId)) {
			return TenantDataSourceProvider.TenantId;
		}
		return "Default";
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
