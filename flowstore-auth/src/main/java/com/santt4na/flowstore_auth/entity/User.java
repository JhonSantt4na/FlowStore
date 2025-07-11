package com.santt4na.flowstore_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_users")
public class User implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "user_name", unique = true, nullable = false)
	private String name;
	
	@Email
	@NotNull
	@Column(unique = true, nullable = false)
	private String email;
	
	@NotNull
	@Column(nullable = false)
	private String password;
	
	private boolean gender;
	
	private String phone;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public User() {
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}