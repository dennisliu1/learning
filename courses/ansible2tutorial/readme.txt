https://serversforhackers.com/c/an-ansible2-tutorial

ansible-playbook -i ./hosts server.yml --ask-pass --ask-become-pass
den, den

to setup the VM to try this scenario in:
download ubuntu ISO (I used desktop edition)
- when installing, set user = den, password = den
- configure the VM to connect to the host system
  - open the VM's settings, and add a host-only adapter
  - set the server static IP to something specific
