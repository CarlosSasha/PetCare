package com.pet.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // Ideal para manejar dinero (monto_total) con precisión económica
import java.time.LocalDate;
import java.time.LocalTime; // Para el manejo exacto de la columna TIME de MySQL

@Entity
@Table(name = "tb_cita")
public class Cita {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int idCita;

    /*CONEXIONES EN CASCADA (Llaves Foráneas al inicio después del ID)*/

    // 1. Conexión con Cliente: Muchas citas pueden ser registradas por el mismo dueño
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    // 2. Conexión con Mascota: Muchas citas pueden pertenecer al mismo paciente
    @ManyToOne
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;

    // 3. Conexión con Medico: Muchas citas pueden ser atendidas por el mismo veterinario
    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;


    @Column(name = "fecha_cita", nullable = false)
    private LocalDate fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private LocalTime horaCita;

    @Column(name = "motivo", length = 255, nullable = false)
    private String motivo;

    @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "estado_cita", length = 20)
    private String estadoCita;

    public Cita() {
        super();
    }

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public LocalDate getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(LocalDate fechaCita) {
		this.fechaCita = fechaCita;
	}

	public LocalTime getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(LocalTime horaCita) {
		this.horaCita = horaCita;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(String estadoCita) {
		this.estadoCita = estadoCita;
	}
    
}
