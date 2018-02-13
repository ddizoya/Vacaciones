package net.vacaciones.entity;
// Generated Feb 10, 2018 5:19:38 PM by Hibernate Tools 5.0.6.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MigrationsId generated by hbm2java
 */
@Embeddable
public class MigrationsId implements java.io.Serializable {

	private String migration;
	private int batch;

	public MigrationsId() {
	}

	public MigrationsId(String migration, int batch) {
		this.migration = migration;
		this.batch = batch;
	}

	@Column(name = "migration", nullable = false)
	public String getMigration() {
		return this.migration;
	}

	public void setMigration(String migration) {
		this.migration = migration;
	}

	@Column(name = "batch", nullable = false)
	public int getBatch() {
		return this.batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigrationsId))
			return false;
		MigrationsId castOther = (MigrationsId) other;

		return ((this.getMigration() == castOther.getMigration()) || (this.getMigration() != null
				&& castOther.getMigration() != null && this.getMigration().equals(castOther.getMigration())))
				&& (this.getBatch() == castOther.getBatch());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMigration() == null ? 0 : this.getMigration().hashCode());
		result = 37 * result + this.getBatch();
		return result;
	}

}
