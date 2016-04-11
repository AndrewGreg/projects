package edu.ben.template.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class User implements UserDetails, CredentialsContainer {

	/* serializable */
	private static final long serialVersionUID = 463391226679612733L;
	@NotNull(message = "Email address is required")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid email")
	@Size(min = 7, max = 45, message = "Must be between 7-45 characters")
	private String email;

	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid email")
	@Size(min = 7, max = 45, message = "Must be between 7-45 characters")
	private String personalEmail;

	public static final Long NULL = 0L;
	// this needs to be 0 for mysql to be cool
	private Long id = User.NULL;

	private int securityLevel;

	private int bNumber;
	private String password;
	private String salt;
	private int titleID;
	private String firstName;
	private String lastName;
	private int role;
	private int graduationYear;
	private String occupation;
	private String company;
	private String suffix;
	private String biography;
	private String experience;
	private boolean hidden;
	private boolean active;
	private DateTime created;
	private DateTime lastActive;
	private DateTime lastModified;
	private String socialMedia;
	private String phoneNumber;
	private String workNumber;
	private boolean userVerified;
	private boolean adminVerified;
	private boolean graduateVerified;
	private boolean currentGraduateVerified;
	private String graduateSchool;
	private int toPublic;
	public String reference;

	// TODO add ArrayList<String> Major(s), ArrayList<String> Minor(s),
	// ArrayList<String> Concentration(s)
	private ArrayList<Major> major;
	private ArrayList<Major> minor;
	private ArrayList<Major> concentration;
	private ArrayList<Interest> interest;

	/* Spring security fields */
	private DateTime lastLogin;
	private Set<GrantedAuthority> authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	/* Whether they are a super user */
	private boolean isSuper;

	/**
	 * Construct the <code>User</code> with the details required by
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
	 * .
	 * 
	 * @param username
	 *            the username presented to the
	 *            <code>DaoAuthenticationProvider</code>
	 * @param password
	 *            the password that should be presented to the
	 *            <code>DaoAuthenticationProvider</code>
	 * @param enabled
	 *            set to <code>true</code> if the user is enabled
	 * @param accountNonExpired
	 *            set to <code>true</code> if the account has not expired
	 * @param credentialsNonExpired
	 *            set to <code>true</code> if the credentials have not expired
	 * @param accountNonLocked
	 *            set to <code>true</code> if the account is not locked
	 * @param authorities
	 *            the authorities that should be granted to the caller if they
	 *            presented the correct username and password and the user is
	 *            enabled. Not null.
	 * 
	 * @throws IllegalArgumentException
	 *             if a <code>null</code> value was passed either as a parameter
	 *             or as an element in the <code>GrantedAuthority</code>
	 *             collection
	 */
	public User() {
		this.enabled = true;
		this.accountNonExpired = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
		this.concentration = new ArrayList<Major>();
		this.minor = new ArrayList<Major>();
		this.major = new ArrayList<Major>();
		this.interest = new ArrayList<Interest>();
	}

	public User(String email, String personalEmail, Long id, int securityLevel, int bNumber, String password,
			String salt, int titleID, String firstName, String lastName, int role, int graduationYear,
			String occupation, String company, String suffix, String biography, String experience, boolean hidden,
			boolean active, DateTime created, DateTime lastActive, DateTime lastModified, String socialMedia,
			String phoneNumber, String workNumber, boolean userVerified, boolean adminVerified, boolean graduateVerified,
			boolean currentGraduateVerified, String graduateSchool, int toPublic, String reference,
			DateTime lastLogin) {
		super();
		this.email = email;
		this.personalEmail = personalEmail;
		this.id = id;
		this.securityLevel = securityLevel;
		this.bNumber = bNumber;
		this.password = password;
		this.salt = salt;
		this.titleID = titleID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.graduationYear = graduationYear;
		this.occupation = occupation;
		this.company = company;
		this.suffix = suffix;
		this.biography = biography;
		this.experience = experience;
		this.hidden = hidden;
		this.active = active;
		this.created = created;
		this.lastActive = lastActive;
		this.lastModified = lastModified;
		this.socialMedia = socialMedia;
		this.phoneNumber = phoneNumber;
		this.workNumber = workNumber;
		this.userVerified = userVerified;
		this.adminVerified = adminVerified;
		this.graduateVerified = graduateVerified;
		this.currentGraduateVerified = currentGraduateVerified;
		this.graduateSchool = graduateSchool;
		this.toPublic = toPublic;
		this.reference = reference;
		this.lastLogin = lastLogin;
		this.enabled = true;
		this.accountNonExpired = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
		this.concentration = new ArrayList<Major>();
		this.minor = new ArrayList<Major>();
		this.major = new ArrayList<Major>();
		this.interest = new ArrayList<Interest>();
	}

	public User(String email, String personalEmail, Long id, int securityLevel, int bNumber, String password,
			String salt, int titleID, String firstName, String lastName, int role, int graduationYear,
			String occupation, String company, String suffix, String biography, String experience, boolean hidden,
			boolean active, DateTime created, DateTime lastActive, DateTime lastModified, String socialMedia,
			String phoneNumber, String workNumber, boolean userVerified, boolean adminVerified, boolean graduateVerified,
			boolean currentGraduateVerified, String graduateSchool, int toPublic, String reference,
			ArrayList<Major> major, ArrayList<Major> minor, ArrayList<Major> concentration,
			ArrayList<Interest> interest, DateTime lastLogin) {
		super();
		this.email = email;
		this.personalEmail = personalEmail;
		this.id = id;
		this.securityLevel = securityLevel;
		this.bNumber = bNumber;
		this.password = password;
		this.salt = salt;
		this.titleID = titleID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.graduationYear = graduationYear;
		this.occupation = occupation;
		this.company = company;
		this.suffix = suffix;
		this.biography = biography;
		this.experience = experience;
		this.hidden = hidden;
		this.active = active;
		this.created = created;
		this.lastActive = lastActive;
		this.lastModified = lastModified;
		this.socialMedia = socialMedia;
		this.phoneNumber = phoneNumber;
		this.workNumber = workNumber;
		this.userVerified = userVerified;
		this.adminVerified = adminVerified;
		this.graduateVerified = graduateVerified;
		this.currentGraduateVerified = currentGraduateVerified;
		this.graduateSchool = graduateSchool;
		this.toPublic = toPublic;
		this.reference = reference;
		this.major = major;
		this.minor = minor;
		this.concentration = concentration;
		this.interest = interest;
		this.lastLogin = lastLogin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		// serializable
		private static final long serialVersionUID = -4713441153411394321L;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before
			// adding it to the set.
			// If the authority is null, it is a custom authority and should
			// precede others.
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}

	/**
	 * Returns {@code true} if the supplied object is a {@code User} instance
	 * with the same {@code username} value.
	 * <p>
	 * In other words, the objects are equal if they have the same username,
	 * representing the same principal.
	 */
	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof User) {
			User user = (User) rhs;
			if (email != null && user.email != null) {
				return email.equals(user.email);
			}
		}
		return false;
	}

	/* TODO might want to override HashCode... */

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getTitleID() {
		return titleID;
	}

	public void setTitleID(int titleID) {
		this.titleID = titleID;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public int getSecurityLevel() {
		return securityLevel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getLastActive() {
		return lastActive;
	}

	public void setLastActive(DateTime lastActive) {
		this.lastActive = lastActive;
	}

	public DateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}

	public String getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public boolean isUserVerified() {
		return userVerified;
	}

	public void setUserVerified(boolean userVerified) {
		this.userVerified = userVerified;
	}

	public boolean isAdminVerified() {
		return adminVerified;
	}

	public void setAdminVerified(boolean adminVerified) {
		this.adminVerified = adminVerified;
	}

	public boolean isGraduateVerified() {
		return graduateVerified;
	}

	public void setGraduateVerified(boolean graduateVerified) {
		this.graduateVerified = graduateVerified;
	}

	public boolean isCurrentGraduateVerified() {
		return currentGraduateVerified;
	}

	public void setCurrentGraduateVerified(boolean currentGraduateVerified) {
		this.currentGraduateVerified = currentGraduateVerified;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public int getToPublic() {
		return toPublic;
	}

	public void setToPublic(int toPublic) {
		this.toPublic = toPublic;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Long getNull() {
		return NULL;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public DateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(DateTime lastLogin) {
		this.lastLogin = lastLogin;

	}

	public String getBiography() {
		return biography;
	}

	public void setBio(String biography) {
		this.biography = biography;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public ArrayList<Major> getMajor() {
		return major;
	}

	public Major getMajorAtIndex(int i) {

		if (i < this.major.size()) {
			return this.major.get(i);
		}

		return null;

	}

	public void setMajor(ArrayList<Major> major) {
		this.major = major;
	}

	public void addMajor(Major major) {

		for (int i = 0; i < this.major.size(); i++) {

			Major m = this.major.get(i);
			if (m.equals(major)) {
				return;
			}
		}
		this.major.add(major);
	}

	public void clearMajors() {
		this.major.clear();
	}

	public void changeMajor(int i, Major n) {

		if (i < this.major.size() && !this.major.get(i).equals(n)) {
			this.major.remove(i);
		}
		if (n != null) {
			addMajor(n);
		}
	}

	public void changeMajor(Major n, Major o) {

		for (int i = 0; i < this.major.size(); i++) {
			Major m = getMajorAtIndex(i);
			if (m.equals(o)) {
				changeMajor(i, n);
			}

		}

		addMajor(n);
	}

	public void deleteMajor(Major o) {

		for (int i = 0; i < this.major.size(); i++) {
			Major m = getMajorAtIndex(i);
			if (m.equals(o)) {
				this.major.remove(i);
				this.major.add(o);
			}

		}
	}

	public ArrayList<Major> getMinor() {
		return minor;
	}

	public void setMinor(ArrayList<Major> minor) {
		this.minor = minor;
	}

	public void clearMinor() {
		this.minor.clear();
	}

	public void addMinor(Major minor) {

		for (int i = 0; i < this.minor.size(); i++) {

			Major m = this.minor.get(i);
			if (m.equals(minor)) {
				return;
			}
		}
		this.minor.add(minor);
	}

	public ArrayList<Major> getConcentration() {
		return concentration;
	}

	public void setConcentration(ArrayList<Major> concentration) {
		this.concentration = concentration;
	}

	public void clearConcentration() {
		this.concentration.clear();
	}

	public void addConcentration(Major concentration) {

		for (int i = 0; i < this.concentration.size(); i++) {

			Major c = this.concentration.get(i);
			if (c.equals(concentration)) {
				return;
			}
		}
		this.concentration.add(concentration);
	}

	public ArrayList<Interest> getInterest() {
		return interest;
	}

	public void setInterest(ArrayList<Interest> interest) {
		this.interest = interest;
	}

	public void clearInterest() {
		this.interest.clear();
	}

	public void addInterest(Interest interest) {

		for (int i = 0; i < this.interest.size(); i++) {

			Interest m = this.interest.get(i);
			if (m.equals(interest)) {
				return;
			}
		}
		this.interest.add(interest);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "User [email=" + email + ", personalEmail=" + personalEmail + ", id=" + id + ", securityLevel="
				+ securityLevel + ", bNumber=" + bNumber + ", password=" + password + ", salt=" + salt + ", titleID="
				+ titleID + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
				+ ", graduationYear=" + graduationYear + ", occupation=" + occupation + ", company=" + company
				+ ", suffix=" + suffix + ", biography=" + biography + ", experience=" + experience + ", hidden="
				+ hidden + ", active=" + active + ", created=" + created + ", lastActive=" + lastActive
				+ ", lastModified=" + lastModified + ", socialMedia=" + socialMedia + ", phoneNumber=" + phoneNumber
				+ ", workNumber=" + workNumber + ", userVerified=" + userVerified + ", adminVerified=" + adminVerified
				+ ", graduateVerified=" + graduateVerified + ", currentGraduateVerified=" + currentGraduateVerified
				+ ", graduateSchool=" + graduateSchool + ", toPublic=" + toPublic + ", reference=" + reference
				+ ", major=" + (major != null ? major.subList(0, Math.min(major.size(), maxLen)) : null) + ", minor="
				+ (minor != null ? minor.subList(0, Math.min(minor.size(), maxLen)) : null) + ", concentration="
				+ (concentration != null ? concentration.subList(0, Math.min(concentration.size(), maxLen)) : null)
				+ ", interest=" + (interest != null ? interest.subList(0, Math.min(interest.size(), maxLen)) : null)
				+ ", lastLogin=" + lastLogin + "]";
	}

}
