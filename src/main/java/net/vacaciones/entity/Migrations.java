package net.vacaciones.entity;
// Generated Feb 10, 2018 5:19:38 PM by Hibernate Tools 5.0.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Migrations generated by hbm2java
 */
@Entity
@Table(name = "migrations", catalog = "vacaciones")
public class Migrations implements java.io.Serializable {

	private MigrationsId id;

	public Migrations() {
	}

	public Migrations(MigrationsId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "migration", column = @Column(name = "migration", nullable = false)),
			@AttributeOverride(name = "batch", column = @Column(name = "batch", nullable = false)) })
	public MigrationsId getId() {
		return this.id;
	}

	public void setId(MigrationsId id) {
		this.id = id;
	}

}
